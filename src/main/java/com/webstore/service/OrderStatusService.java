package com.webstore.service;

import com.webstore.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    List<OrderStatus> getOrderStatuses();
}
