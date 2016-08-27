package com.webstore.service;


import com.webstore.entity.Customer;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Customer getCustomerById(Integer customerId);
    boolean createCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deactivateCustomer(Integer customerId);
    List<Customer> getAllCustomers(String name, Integer personalId, Date birthDate, String address);
    String showErrorMessage();
}
