package com.tt.controller;

import com.tt.service.ProductService;
import lombok.Setter;

/**
 * 产品控制器：处理产品相关的业务逻辑
 */
public class ProductController {

    @Setter
    private ProductService productService;

    /**
     * 获取产品信息
     * @return 产品信息
     */
    public String getProduct() {

        return productService.getProduct();
    }
}
