package com.webstore.serviceImpl;

import com.webstore.dao.CustomerDao;
import com.webstore.dao.CustomerStatusDao;
import com.webstore.entity.CustomerStatus;
import com.webstore.service.CustomerStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {

    @Autowired
    CustomerStatusDao customerStatusDao;

    @Override
    public List<CustomerStatus> getCustomerStatuses() {
        return customerStatusDao.getCustomerStatuses();
    }
}
