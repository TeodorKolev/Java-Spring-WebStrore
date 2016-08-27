package com.webstore.daoImpl;

import com.webstore.dao.OrderDao;
import com.webstore.entity.Customer;
import com.webstore.entity.Order;
import com.webstore.entity.User;
import org.hibernate.*;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Secured("ROLE_ADMIN")
    public List<Order> getAllOrders(String productName, String customerName, Date purchaseDate) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Order.class);
        if (productName!=null && !productName.isEmpty()) {
            crit.createAlias("product", "product");
            crit.add(Restrictions.eq("product.name", productName));
        }
        if (customerName!=null && !customerName.isEmpty()) {
            crit.createAlias("customer", "customer");
            crit.add(Restrictions.eq("customer.name", customerName));
        }
        if (purchaseDate!=null) {
            Date minDate = new Date(purchaseDate.getTime());
            Date maxDate = new Date(purchaseDate.getTime() + TimeUnit.DAYS.toMillis(1));
            crit.add(Restrictions.ge("purchaseDate", minDate));
            crit.add(Restrictions.lt("purchaseDate", maxDate));
        }
        List<Order> allOrders = crit.list();
        return allOrders;
    }

    public List<Order> getUserOrders(String productName, String customerName, Date purchaseDate, Integer userId) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("userId", userId));
        if (productName!=null && !productName.isEmpty()) {
            crit.createAlias("product", "product");
            crit.add(Restrictions.eq("product.name", productName));
        }
        if (customerName!=null && !customerName.isEmpty()) {
            crit.createAlias("customer", "customer");
            crit.add(Restrictions.eq("customer.name", customerName));
        }
        if (purchaseDate!=null) {
            Date minDate = new Date(purchaseDate.getTime());
            Date maxDate = new Date(purchaseDate.getTime() + TimeUnit.DAYS.toMillis(1));
            crit.add(Restrictions.ge("purchaseDate", minDate));
            crit.add(Restrictions.lt("purchaseDate", maxDate));
        }
        List<Order> userOrders = crit.list();
        return userOrders;
    }

    public Order getOrderById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", id));
        Order order = (Order) crit.list().get(0);
        session.close();
        return order;
    }

    @Transactional
    public void updateOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(order);
        tx.commit();
        session.close();
    }

    @Transactional
    public void createOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(order);
        tx.commit();
        session.close();
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deactivateOrder(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", id));
        Order order = (Order) crit.list().get(0);
        order.setStatus(2);
        session.update(order);
        tx.commit();
        session.close();
    }
}
