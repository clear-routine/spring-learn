package com.tt.controller;

import com.tt.service.ProductService;

import java.util.UUID;

/**
 * 产品控制器，singleton：每次从 Spring 容器 getBean 获取，不在 ServletContext 中
 */
public class ProductController {

    private final String instanceId = UUID.randomUUID().toString();

    private int accessCount;

    private ProductService productService;

    public void setProductService(ProductService productService) {

        this.productService = productService;
    }

    public String getProduct() {

        return productService.getProduct();
    }

    public void incrementAccess() {

        accessCount++;
    }

    public String getInstanceId() {

        return instanceId;
    }

    public int getAccessCount() {

        return accessCount;
    }
}
