package com.safar.service;

import com.safar.entity.CurrentAdminSession;
import com.safar.entity.CurrentUserSession;
import com.safar.entity.User;
import com.safar.exception.DataNotFoundException;
import com.safar.repository.CurrentAdminSessionRepository;
import com.safar.repository.CurrentUserSessionRepository;
import com.safar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUserSessionRepository userSessionRepository;

    @Autowired
    private CurrentAdminSessionRepository adminSession;

    @Override
    public User createUser(User user) throws DataNotFoundException {
        User registeredUser = userRepository.findByEmail(user.getMobile());
        if (registeredUser != null) throw new DataNotFoundException("User is already registered!");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, String key) throws DataNotFoundException {
        CurrentUserSession loggedInUser = userSessionRepository.findByUuid(key);
        if (loggedInUser == null) throw new DataNotFoundException("Please enter a valid key or login first!");
        if (user.getUserID() != loggedInUser.getUserID())
            throw new DataNotFoundException("Invalid user details, please login for updating user!");
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Integer userID, String key) throws DataNotFoundException, DataNotFoundException {
        CurrentAdminSession loggedInAdmin = adminSession.findByAid(key);
        if (loggedInAdmin == null) throw new DataNotFoundException("Please enter a valid key or login first!");
        User user = userRepository.findById(userID).orElseThrow(() -> new DataNotFoundException("Invalid user Id!"));
        userRepository.delete(user);
        return user;
    }

    @Override
    public User viewUserById(Integer userID, String key) throws DataNotFoundException, DataNotFoundException {
        CurrentAdminSession loggedInAdmin = adminSession.findByAid(key);
        if (loggedInAdmin == null) throw new DataNotFoundException("Please enter a valid key or login first!");

        User user = userRepository.findById(userID).orElseThrow(() -> new DataNotFoundException("Invalid user Id!"));
        return user;
    }

    @Override
    public List<User> viewAllUser(String key) throws DataNotFoundException, DataNotFoundException {
        CurrentAdminSession loggedInAdmin = adminSession.findByAid(key);
        if (loggedInAdmin == null) throw new DataNotFoundException("Please enter a valid key or login first!");
        List<User> list = (List<User>) userRepository.findAll();
        if (list.isEmpty()) throw new DataNotFoundException("No users found!");
        return list;
    }
}
