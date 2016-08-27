package com.webstore.daoImpl;

import com.webstore.dao.OrderStatusDao;
import com.webstore.entity.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {


    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<OrderStatus> getOrderStatuses() {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(OrderStatus.class);
        List<OrderStatus> allOrderStatuses = crit.list();
        return allOrderStatuses;
    }
}
