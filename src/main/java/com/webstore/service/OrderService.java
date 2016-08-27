package com.webstore.service;

import com.webstore.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders(String productName, String customerName, Date purchaseDate);
    List<Order> getUserOrders(String productName, String customerName, Date purchaseDate, Integer userId);
    Order getOrderById(Integer orderId);
    boolean updateOrder(Order order);
    boolean createOrder(Order order);
    boolean deactivateOrder(Integer orderId);
    String showErrorMessage();
}
