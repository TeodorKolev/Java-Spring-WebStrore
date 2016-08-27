package com.webstore.controller;

import com.constant.Constant;
import com.webstore.dto.CustomerSearch;
import com.webstore.entity.Customer;
import com.webstore.serviceImpl.CustomerServiceImpl;
import com.webstore.serviceImpl.CustomerStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomersController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    CustomerStatusServiceImpl customerStatusService;

    private String errorMessage;

    @RequestMapping(value = Constant.CUSTOMERS_URL, method = RequestMethod.GET)
    public String listCustomers(Model model, @ModelAttribute("CustomerSearch") CustomerSearch customerSearch){
        model.addAttribute(Constant.CUSTOMERS, customerService.getAllCustomers(customerSearch.getName(),
                customerSearch.getPersonalId(), customerSearch.getBirthDate(), customerSearch.getAddress()));
        model.addAttribute("customersUrl", Constant.CUSTOMERS_URL);
        model.addAttribute("customerAddUrl", Constant.CUSTOMER_ADD_URL);
        model.addAttribute("customerEditNoIdUrl", Constant.CUSTOMER_EDIT_NO_ID_URL);
        model.addAttribute("customerDeactivateNoIdUrl", Constant.CUSTOMER_DEACTIVATE_NO_ID_URL);
        return Constant.CUSTOMERS;
    }

    @RequestMapping(value = Constant.CUSTOMER_URL, method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model){
        boolean successCreateCustomer;
        boolean successEditCustomer;

        if (customer.getId() == null) {
            successCreateCustomer = customerService.createCustomer(customer);
            if (successCreateCustomer) {
                return Constant.CUSTOMERS_REDIRECT_URL;
            } else {
                errorMessage = customerService.showErrorMessage();
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return addCustomer(customer, model);
            }
        } else {
            successEditCustomer = customerService.updateCustomer(customer);
            if (successEditCustomer) {
                return Constant.CUSTOMERS_REDIRECT_URL;
            } else {
                model.addAttribute(Constant.STATUSES, customerStatusService.getCustomerStatuses());
                errorMessage = customerService.showErrorMessage();
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return editCustomer(customer.getId(), model);
            }
        }
    }

    @RequestMapping(Constant.CUSTOMER_ADD_URL)
    public String addCustomer(@ModelAttribute("customer") Customer customer, Model model){
        model.addAttribute("customerEditNoIdUrl", Constant.CUSTOMER_EDIT_NO_ID_URL);
        model.addAttribute("customerUrl", Constant.CUSTOMER_URL);
        return Constant.CUSTOMER_FORM_URL;
    }

    @RequestMapping(Constant.CUSTOMER_EDIT_URL)
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customerEditNoIdUrl", Constant.CUSTOMER_EDIT_NO_ID_URL);
        model.addAttribute("customerUrl", Constant.CUSTOMER_URL);
        model.addAttribute(Constant.CUSTOMER, customerService.getCustomerById(id));
        model.addAttribute(Constant.STATUSES, customerStatusService.getCustomerStatuses());
        return Constant.CUSTOMER_FORM_URL;
    }

    @RequestMapping(Constant.CUSTOMER_DEACTIVATE_URL)
    public String deactivateCustomer(@PathVariable Integer id){
        customerService.deactivateCustomer(id);
        return Constant.CUSTOMERS_REDIRECT_URL;
    }

}
