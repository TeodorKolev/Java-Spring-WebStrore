package com.webstore.daoImpl;

import com.webstore.dao.UserRoleDao;
import com.webstore.entity.UserRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<UserRole> getUserRoles() {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(UserRole.class);
        List<UserRole> allUserRoles = crit.list();
        return allUserRoles;
    }
}
