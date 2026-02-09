package com.tt.controller;

import com.tt.service.ProductService;

/**
 * 产品控制器：处理产品相关的业务逻辑
 */
public class ProductController {

    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 获取产品信息
     * @return 产品信息
     */
    public String getProduct() {
        return productService.getProduct();
    }
}
