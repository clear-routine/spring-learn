package com.tt.product.service;

import com.tt.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品服务层，singleton
 */
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public String getProduct() {

        return productRepository.getProduct();
    }
}
