package com.webstore.serviceImpl;

import com.constant.Constant;
import com.webstore.dao.ProductDao;
import com.webstore.entity.Product;
import com.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    private String errorMessage;

    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    public boolean createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty() ||
                product.getType() == null || product.getType().isEmpty() ||
                product.getPrice() == null ||
                product.getQuantity() == null) {
            errorMessage = Constant.ERR_ALL_FIELDS;
            return false;
        } else {
            productDao.createProduct(product);
            return true;
        }

    }

    public boolean updateProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty() ||
                product.getType() == null || product.getType().isEmpty() ||
                product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0||
                product.getQuantity() == null || product.getQuantity() < 0) {
            errorMessage = Constant.ERR_ALL_FIELDS;
            return false;
        } else {
            productDao.updateProduct(product);
            return true;
        }
    }

    public boolean deleteProductById(Integer productId) {
        productDao.deleteProduct(productId);
        return true;
    }

    public List<Product> getAllProducts(String name, BigDecimal price, String type, Integer quantity) {
        return productDao.getAllProducts(name, price, type, quantity);
    }

    @Override
    public String showErrorMessage() {
        return errorMessage;
    }
}
