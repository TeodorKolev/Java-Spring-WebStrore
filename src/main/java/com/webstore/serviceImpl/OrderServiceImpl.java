package com.webstore.serviceImpl;

import com.constant.Constant;
import com.webstore.dao.OrderDao;
import com.webstore.dao.ProductDao;
import com.webstore.entity.Order;
import com.webstore.entity.Product;
import com.webstore.entity.User;
import com.webstore.security.UserLogin;
import com.webstore.service.OrderService;
import com.webstore.utility.UserLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserLoginUtils userLoginUtils;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ProductDao productDao;

    private String errorMessage;

    public List<Order> getAllOrders(String productName, String customerName, Date purchaseDate) {
        return orderDao.getAllOrders(productName, customerName, purchaseDate);
    }

    public List<Order> getUserOrders(String productName, String customerName, Date purchaseDate, Integer userId) {
        return orderDao.getUserOrders(productName, customerName, purchaseDate, userId);
    }

    public Order getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }

    public boolean updateOrder(Order order) {
        Date now = new Date();
        String loggedUserName = userLoginUtils.getUserLogin().getUsername();
        User loggedUser = userService.getUserByUsername(loggedUserName);
        Integer loggerUserId = loggedUser.getId();
        order.setUserId(loggerUserId);
        order.setPurchaseDate(now);

        Integer orderProductId = order.getProductId();
        Product product = productDao.getProductById(orderProductId);

        if (product.getQuantity() < order.getQuantity()){
            errorMessage = Constant.ERR_INSUFFICIENT_QUANTITY;
            return false;
        }
        else {
            product.setQuantity(product.getQuantity() - order.getQuantity());
            productDao.updateProduct(product);
            orderDao.updateOrder(order);
            return true;
        }
    }

    public boolean createOrder(Order order) {
        Date now = new Date();
        Integer loggedUserId = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        order.setUserId(loggedUserId);
        order.setPurchaseDate(now);
        order.setStatus(1);

        Integer orderProductId = order.getProductId();
        Product product = productDao.getProductById(orderProductId);

        if (order.getQuantity() == null) {
            errorMessage = Constant.ERR_ALL_FIELDS;
            return false;
        }
        if (product.getQuantity() < order.getQuantity()){
            errorMessage = Constant.ERR_INSUFFICIENT_QUANTITY;
            return false;
        }
        else {
            product.setQuantity(product.getQuantity() - order.getQuantity());
            productDao.updateProduct(product);
            orderDao.createOrder(order);
            return true;
        }
    }

    @Override
    public boolean deactivateOrder(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        Integer orderProductId = order.getProductId();
        Product product = productDao.getProductById(orderProductId);
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productDao.updateProduct(product);
        orderDao.deactivateOrder(orderId);
        return true;
    }

    @Override
    public String showErrorMessage() {
        return errorMessage;
    }
}
