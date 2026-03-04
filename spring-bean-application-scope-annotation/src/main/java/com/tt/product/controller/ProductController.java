package com.tt.product.controller;

import com.tt.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * 产品控制器，singleton：每次从「新容器」getBean 获取，不同容器 = 不同实例
 */
@Controller
public class ProductController {

    private final String instanceId = UUID.randomUUID().toString();

    private int accessCount;

    private ProductService productService;

    @Autowired
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
