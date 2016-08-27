package com.webstore.daoImpl;

import com.webstore.dao.ProductDao;
import com.webstore.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;

    public List<Product> getAllProducts(String name, BigDecimal price, String type, Integer quantity) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Product.class);
        if (name!=null && !name.isEmpty()) {
            crit.add(Restrictions.eq("name", name));
        }
        if (price!=null) {
            crit.add(Restrictions.eq("price", price));
        }
        if (type!=null && !type.isEmpty()) {
            crit.add(Restrictions.eq("type", type));
        }
        if (quantity!=null) {
            crit.add(Restrictions.eq("quantity", quantity));
        }
        List<Product> allProducts = crit.list();
        return allProducts;
    }

    public Product getProductById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("id", id));
        Product product = (Product) crit.list().get(0);
        session.close();
        return product;
    }

    @Transactional
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(product);
        tx.commit();
        session.close();
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public void createProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteProduct(Integer productId) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Product product = (Product) session.load(Product.class, new Integer(productId));
        session.delete(product);
        tx.commit();
        session.close();
    }
}
