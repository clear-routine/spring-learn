package com.tt.service;

import com.tt.repository.ProductRepository;

/**
 * 产品服务层：处理产品相关的业务逻辑
 */
public class ProductService {

    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 获取产品信息
     * @return 产品信息
     */
    public String getProduct() {
        return productRepository.getProduct();
    }
}
