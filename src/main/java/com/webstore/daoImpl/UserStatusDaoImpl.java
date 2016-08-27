package com.webstore.daoImpl;

import com.webstore.dao.UserStatusDao;
import com.webstore.entity.UserStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStatusDaoImpl implements UserStatusDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<UserStatus> getUserStatuses() {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(UserStatus.class);
        List<UserStatus> allUserStatuses = crit.list();
        return allUserStatuses;
    }
}
