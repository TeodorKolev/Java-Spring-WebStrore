package com.webstore.controller;

import com.constant.Constant;
import com.webstore.dto.OrderSearch;
import com.webstore.entity.Order;
import com.webstore.security.UserLogin;
import com.webstore.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrdersController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    OrderStatusServiceImpl orderStatusService;

    @Autowired
    UserServiceImpl userService;

    private String errorMessage;

    @RequestMapping(value = Constant.ORDERS_URL, method = RequestMethod.GET)
    public String listOrders(Model model, @ModelAttribute("OrderSearch") OrderSearch orderSearch){
        Integer loggedUserId = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Integer loggedUserRoleId = userService.getUserById(loggedUserId).getRole();
        if (loggedUserRoleId == 1) {
            model.addAttribute(Constant.ORDERS, orderService.getAllOrders(orderSearch.getProductName(),
                    orderSearch.getCustomerName(), orderSearch.getPurchaseDate()));
        } else {
            model.addAttribute(Constant.ORDERS, orderService.getUserOrders(orderSearch.getProductName(),
                    orderSearch.getCustomerName(), orderSearch.getPurchaseDate(), loggedUserId));
        }
        model.addAttribute("ordersUrl", Constant.ORDERS_URL);
        model.addAttribute("orderAddUrl", Constant.ORDER_ADD_URL);
        model.addAttribute("orderEditNoIdUrl", Constant.ORDER_EDIT_NO_ID_URL);
        model.addAttribute("orderDeactivateNoIdUrl", Constant.ORDER_DEACTIVATE_NO_ID_URL);
        return Constant.ORDERS;
    }

    @RequestMapping(value = Constant.ORDER_URL, method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("order") Order order, Model model) {
        boolean successCreateOrder;
        boolean successEditOrder;
        if (order.getId() == null) {
            successCreateOrder = orderService.createOrder(order);
            if (successCreateOrder) {
                return Constant.ORDERS_REDIRECT_URL;
            } else {
                errorMessage = orderService.showErrorMessage();
                model.addAttribute(Constant.PRODUCTS, productService.getAllProducts(null, null, null, null));
                model.addAttribute(Constant.CUSTOMERS, customerService.getAllCustomers(null, null, null, null));
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return addOrder(order, model);
            }
        } else {
            successEditOrder = orderService.updateOrder(order);
            if (successEditOrder) {
                return Constant.ORDERS_REDIRECT_URL;
            } else {
                errorMessage = orderService.showErrorMessage();
                model.addAttribute(Constant.PRODUCTS, productService.getAllProducts(null, null, null, null));
                model.addAttribute(Constant.CUSTOMERS, customerService.getAllCustomers(null, null, null, null));
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return editOrder(order.getId(), model);
            }
        }
    }

    @RequestMapping(Constant.ORDER_ADD_URL)
    public String addOrder(@ModelAttribute("order") Order order, Model model){
        model.addAttribute(Constant.PRODUCTS, productService.getAllProducts(null, null, null, null));
        model.addAttribute(Constant.CUSTOMERS, customerService.getAllCustomers(null, null, null, null));
        model.addAttribute("orderEditNoIdUrl", Constant.ORDER_EDIT_NO_ID_URL);
        model.addAttribute("orderUrl", Constant.ORDER_URL);
        return Constant.ORDER_FORM_URL;
    }

    @RequestMapping(Constant.ORDER_EDIT_URL)
    public String editOrder(@PathVariable Integer id, Model model){
        model.addAttribute(Constant.PRODUCTS, productService.getAllProducts(null, null, null, null));
        model.addAttribute(Constant.CUSTOMERS, customerService.getAllCustomers(null, null, null, null));
        model.addAttribute(Constant.STATUSES, orderStatusService.getOrderStatuses());
        model.addAttribute(Constant.ORDER, orderService.getOrderById(id));
        model.addAttribute("orderEditNoIdUrl", Constant.ORDER_EDIT_NO_ID_URL);
        model.addAttribute("orderUrl", Constant.ORDER_URL);
        return Constant.ORDER_FORM_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.ORDER_DEACTIVATE_URL)
    public String deactivateOrder(@PathVariable Integer id){
        orderService.deactivateOrder(id);
        return Constant.ORDERS_REDIRECT_URL;
    }

}
