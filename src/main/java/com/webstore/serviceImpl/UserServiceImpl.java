package com.webstore.serviceImpl;

import com.constant.Constant;
import com.webstore.dao.UserDao;
import com.webstore.entity.User;
import com.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    private String errorMessage;

    public List<User> getAllUsers(String username, String name, Integer role, Integer status) {
        return userDao.getAllUsers(username, name, role, status);
    }

    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public boolean updateUser(User user) {
        Integer userId = user.getId();
        String userPass = user.getPassword();
        String dbPass = getUserById(userId).getPassword();

        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getName() == null || user.getName().isEmpty() ||
                user.getRole() == null ||
                user.getStatus() == null) {
            errorMessage = Constant.ERR_ALL_FIELDS;
            return false;
        } else {
            if (!userPass.equals(dbPass)){
                String encodedPassword = encoder.encodePassword(userPass, null);
                user.setPassword(encodedPassword);
            }
            userDao.updateUser(user);
            return true;
        }
    }

    public boolean createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getName() == null || user.getName().isEmpty() ||
                user.getRole() == null ||
                user.getStatus() == null) {
            errorMessage = Constant.ERR_ALL_FIELDS;
            return false;
        } else {
            String encodedPassword = encoder.encodePassword(user.getPassword(), null);
            user.setPassword(encodedPassword);
            userDao.createUser(user);
            return true;
        }
    }

    public boolean deactivateUser(Integer userId) {
        userDao.deactivateUser(userId);
        return true;
    }

    @Override
    public String showErrorMessage() {
        return errorMessage;
    }
}
