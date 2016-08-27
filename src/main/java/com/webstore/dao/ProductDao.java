package com.webstore.dao;

import com.webstore.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts(String name, BigDecimal price, String type, Integer quantity);
    Product getProductById(Integer productId);
    void updateProduct(Product product);
    void createProduct(Product product);
    void deleteProduct(Integer productId);

}
