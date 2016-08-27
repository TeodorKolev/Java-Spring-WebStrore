package com.webstore.daoImpl;


import com.webstore.dao.UserDao;
import com.webstore.entity.User;
import com.webstore.utility.UserLoginUtils;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserLoginUtils userLoginUtils;

    Md5PasswordEncoder encoder = new Md5PasswordEncoder();

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers(String username, String name, Integer role, Integer status) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(User.class, "u");
        if (username!=null && !username.isEmpty()) {
            crit.add(Restrictions.eq("username", username));
        }
        if (name!=null && !name.isEmpty()) {
            crit.add(Restrictions.eq("name", name));
        }
        if (role!=null) {
            crit.add(Restrictions.eq("role", role));
        }
        if (status!=null) {
            crit.add(Restrictions.eq("status", status));
        }
        List<User> allUsers = crit.list();
        return allUsers;
    }

    public User getUserById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("id", id));
        User user = (User) crit.list().get(0);
        session.close();
        return user;
    }

    public User getUserByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.list().get(0);
        session.close();
        return user;
    }

    @Transactional
    public void updateUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }

    @Secured("ROLE_ADMIN")
    @Transactional
    public void createUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public void deactivateUser(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("id", id));
        User user = (User) crit.list().get(0);
        user.setStatus(2);
        session.update(user);
        tx.commit();
        session.close();
    }
}
