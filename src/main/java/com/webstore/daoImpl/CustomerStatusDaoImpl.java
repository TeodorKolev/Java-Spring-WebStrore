package com.webstore.daoImpl;

import com.webstore.dao.CustomerStatusDao;
import com.webstore.entity.Customer;
import com.webstore.entity.CustomerStatus;
import com.webstore.entity.UserRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerStatusDaoImpl implements CustomerStatusDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<CustomerStatus> getCustomerStatuses() {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(CustomerStatus.class);
        List<CustomerStatus> allCustomerStatuses = crit.list();
        return allCustomerStatuses;
    }
}
