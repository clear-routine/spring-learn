package com.tt.product.controller;

import com.tt.product.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * 产品控制器，singleton：每次从「新容器」getBean 获取，不同容器 = 不同实例
 */
@Getter
@Controller
public class ProductController {

    private final String instanceId = UUID.randomUUID().toString();

    private int accessCount;

    @Setter
    @Autowired
    private ProductService productService;

    public String getProduct() {

        return productService.getProduct();
    }

    public void incrementAccess() {

        accessCount++;
    }
}
