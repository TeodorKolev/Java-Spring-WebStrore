package com.webstore.dao;

import com.webstore.entity.CustomerStatus;

import java.util.List;

public interface CustomerStatusDao {
    List<CustomerStatus> getCustomerStatuses();
}
