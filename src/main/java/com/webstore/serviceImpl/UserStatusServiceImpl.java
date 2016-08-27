package com.webstore.serviceImpl;

import com.webstore.dao.UserStatusDao;
import com.webstore.entity.UserStatus;
import com.webstore.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusDao statusDao;

    @Override
    public List<UserStatus> getUserStatuses() {
        return statusDao.getUserStatuses();
    }
}
