package com.safar.service;



import com.safar.entity.User;
import com.safar.exception.DataNotFoundException;

import java.util.List;

public interface UserService {
    User createUser(User user) throws DataNotFoundException;
    User updateUser(User user, String key) throws DataNotFoundException;
    User deleteUser(Integer userId, String key) throws DataNotFoundException, DataNotFoundException;
    User viewUserById(Integer userId, String key) throws DataNotFoundException, DataNotFoundException;
    List<User> viewAllUser(String key) throws DataNotFoundException, DataNotFoundException;
}
