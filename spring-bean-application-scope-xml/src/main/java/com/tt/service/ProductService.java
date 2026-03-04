package com.tt.service;

import com.tt.repository.ProductRepository;

/**
 * 产品服务层，singleton
 */
public class ProductService {

    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public String getProduct() {

        return productRepository.getProduct();
    }
}
