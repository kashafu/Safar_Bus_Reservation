package com.safar.service;


import com.safar.dto.Login;
import com.safar.entity.Admin;
import com.safar.entity.CurrentAdminSession;
import com.safar.exception.DataNotFoundException;

public interface AdminService {
    Admin createAdmin(Admin admin) throws DataNotFoundException;
    Admin updateAdmin(Admin admin, String key) throws DataNotFoundException;
    CurrentAdminSession adminLogin(Login loginDTO) throws DataNotFoundException;
    String adminLogout(String key);
}
