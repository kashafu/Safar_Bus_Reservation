package com.safar.controller;

import com.safar.dto.Login;
import com.safar.entity.Admin;
import com.safar.entity.CurrentAdminSession;
import com.safar.exception.DataNotFoundException;
import com.safar.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/safar")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello()  {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws DataNotFoundException {
        System.out.println("Admin Controller: " + admin);
        Admin savedAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @RequestParam(required = false) String key) throws DataNotFoundException {
        Admin updatedAdmin = adminService.updateAdmin(admin, key);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<CurrentAdminSession> loginAdmin(@RequestBody Login loginDTO) throws DataNotFoundException {
        CurrentAdminSession currentAdminSession = adminService.adminLogin(loginDTO);
        return new ResponseEntity<>(currentAdminSession, HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/logout")
    public String logoutAdmin(@RequestParam(required = false) String key) {
        return adminService.adminLogout(key);
    }
}
