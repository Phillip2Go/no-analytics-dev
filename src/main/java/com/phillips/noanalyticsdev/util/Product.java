package com.phillips.noanalyticsdev.util;

import javax.persistence.Embeddable;

@Embeddable
public class Product {
    private String productName;
    // private String contentPath;
    private String productTag;

    public Product() {
    }

    public Product(String productName, String productTag) {
        this.productName = productName;
        this.productTag = productTag;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }
}
