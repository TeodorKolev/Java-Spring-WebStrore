package com.webstore.service;

import com.webstore.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProductById(Integer productId);
    List<Product> getAllProducts(String name, BigDecimal price, String type, Integer quantity);
    String showErrorMessage();
}
