package com.webstore.dao;

import com.webstore.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders(String productName, String customerName, Date purchaseDate);
    List<Order> getUserOrders(String productName, String customerName, Date purchaseDate, Integer userId);
    Order getOrderById(Integer orderId);
    void updateOrder(Order order);
    void createOrder(Order order);
    void deactivateOrder(Integer orderId);
}
