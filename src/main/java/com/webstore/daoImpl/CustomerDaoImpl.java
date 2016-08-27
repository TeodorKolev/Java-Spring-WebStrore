package com.webstore.daoImpl;

import com.webstore.dao.CustomerDao;
import com.webstore.entity.Customer;
import com.webstore.entity.Order;
import com.webstore.entity.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Customer> getAllCustomers(String name, Integer personalId, Date birthDate, String address) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Customer.class);
        if (name!=null && !name.isEmpty()) {
            crit.add(Restrictions.eq("name", name));
        }
        if (personalId!=null) {
            crit.add(Restrictions.eq("personalId", personalId));
        }
        if (birthDate!=null) {
            Date minDate = new Date(birthDate.getTime());
            Date maxDate = new Date(birthDate.getTime() + TimeUnit.DAYS.toMillis(1));
            crit.add(Restrictions.ge("birthDate", minDate));
            crit.add(Restrictions.lt("birthDate", maxDate));
        }
        if (address!=null && !address.isEmpty()) {
            crit.add(Restrictions.eq("address", address));
        }
        List<Customer> allCustomers = crit.list();
        return allCustomers;
    }

    public Customer getCustomerById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Customer.class);
        crit.add(Restrictions.eq("id", id));
        Customer customer = (Customer) crit.list().get(0);
        session.close();
        return customer;
    }

    @Transactional
    public void updateCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(customer);
        tx.commit();
        session.close();
    }

    @Transactional
    public void createCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(customer);
        tx.commit();
        session.close();
    }

    @Override
    public void deactivateCustomer(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Customer.class);
        crit.add(Restrictions.eq("id", id));
        Customer customer = (Customer) crit.list().get(0);
        customer.setStatus(2);
        session.update(customer);
        tx.commit();
        session.close();
    }
}
