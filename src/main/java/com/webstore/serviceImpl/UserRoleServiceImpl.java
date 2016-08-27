package com.webstore.serviceImpl;

import com.webstore.dao.UserRoleDao;
import com.webstore.entity.UserRole;
import com.webstore.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public List<UserRole> getUserRoles() {
        return userRoleDao.getUserRoles();
    }
}
