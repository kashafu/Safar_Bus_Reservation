package com.safar.service;

import com.safar.dto.Login;
import com.safar.entity.Admin;
import com.safar.entity.CurrentAdminSession;
import com.safar.exception.DataNotFoundException;
import com.safar.repository.AdminRepository;
import com.safar.repository.CurrentAdminSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CurrentAdminSessionRepository adminSessionRepository;

    @Override
    public Admin createAdmin(Admin admin) throws DataNotFoundException {
        List<Admin> admins = adminRepository.findByEmail(admin.getEmail());

        if (!admins.isEmpty()) throw new DataNotFoundException("Admin already present with the given email: " + admin.getEmail());

        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin, String key) throws DataNotFoundException {
        CurrentAdminSession adminSession = adminSessionRepository.findByAid(key);
        if (adminSession == null) throw new DataNotFoundException("Please enter valid key or login first!");
        if (admin.getAdminID() != adminSession.getAdminID()) throw new DataNotFoundException("Invalid admin details, please login for updating admin!");
        return adminRepository.save(admin);
    }

    @Override
    public CurrentAdminSession adminLogin(Login loginDTO) throws DataNotFoundException {
        List<Admin> admins = adminRepository.findByEmail(loginDTO.getEmail());

        if (admins.isEmpty()) throw new DataNotFoundException("Please enter a valid email!");

        Admin registeredAdmin = admins.get(0);

        if (registeredAdmin == null) throw new DataNotFoundException("Please enter a valid email!");

        Optional<CurrentAdminSession> loggedInAdmin = adminSessionRepository.findById(registeredAdmin.getAdminID());
        if (loggedInAdmin.isPresent()) throw new DataNotFoundException("Admin is already loggedIn!");

        if (registeredAdmin.getPassword().equals(loginDTO.getPassword())) {
            SecureRandom secureRandom = new SecureRandom();
            byte[] keyBytes = new byte[10];
            secureRandom.nextBytes(keyBytes);

            String key = Base64.getEncoder().encodeToString(keyBytes);

            CurrentAdminSession adminSession = new CurrentAdminSession();
            adminSession.setAdminID(registeredAdmin.getAdminID());
            adminSession.setAid(key);
            adminSession.setTime(LocalDateTime.now());
            return adminSessionRepository.save(adminSession);
        } else
            throw new DataNotFoundException("Please enter valid password!");
    }

    @Override
    public String adminLogout(String key) {
        CurrentAdminSession currentAdminSession = adminSessionRepository.findByAid(key);
        if (currentAdminSession == null) return "Invalid Admin login key!";
        adminSessionRepository.delete(currentAdminSession);
        return "Admin logged out!";
    }
}
