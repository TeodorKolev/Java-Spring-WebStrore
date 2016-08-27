package com.webstore.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderSearch {
    private String productName;

    private String customerName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
