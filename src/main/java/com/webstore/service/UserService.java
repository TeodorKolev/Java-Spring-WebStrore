package com.webstore.service;

import com.webstore.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(String username, String name, Integer role, Integer status);
    User getUserById(Integer userId);
    User getUserByUsername(String username);
    boolean updateUser(User user);
    boolean createUser(User user);
    boolean deactivateUser(Integer userId);
    String showErrorMessage();
}
