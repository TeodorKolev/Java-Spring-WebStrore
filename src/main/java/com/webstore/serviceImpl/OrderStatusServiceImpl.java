package com.webstore.serviceImpl;


import com.webstore.dao.OrderStatusDao;
import com.webstore.entity.OrderStatus;
import com.webstore.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    OrderStatusDao orderStatusDao;

    @Override
    public List<OrderStatus> getOrderStatuses() {
        return orderStatusDao.getOrderStatuses();
    }
}
