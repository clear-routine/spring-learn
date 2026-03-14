package com.tt.service;

import com.tt.repository.ProductRepository;
import lombok.Setter;

/**
 * 产品服务层，singleton
 */
public class ProductService {

    @Setter
    private ProductRepository productRepository;

    public String getProduct() {

        return productRepository.getProduct();
    }
}
