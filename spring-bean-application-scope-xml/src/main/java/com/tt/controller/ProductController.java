package com.tt.controller;

import com.tt.service.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * 产品控制器，singleton：每次从 Spring 容器 getBean 获取，不在 ServletContext 中
 */
@Getter
public class ProductController {

    private final String instanceId = UUID.randomUUID().toString();

    private int accessCount;

    @Setter
    private ProductService productService;

    public String getProduct() {

        return productService.getProduct();
    }

    public void incrementAccess() {

        accessCount++;
    }
}
