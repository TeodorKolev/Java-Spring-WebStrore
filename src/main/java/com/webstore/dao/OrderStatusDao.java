package com.webstore.dao;


import com.webstore.entity.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    List<OrderStatus> getOrderStatuses();
}
