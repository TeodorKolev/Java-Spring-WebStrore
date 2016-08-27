package com.webstore.dao;

import com.webstore.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers(String username, String name, Integer role, Integer status);
    User getUserById(Integer userId);
    User getUserByUsername(String username);
    void updateUser(User user);
    void createUser(User user);
    void deactivateUser(Integer userId);
}
