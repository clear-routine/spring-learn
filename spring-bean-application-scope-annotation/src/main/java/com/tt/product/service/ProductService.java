package com.tt.product.service;

import com.tt.product.repository.ProductRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品服务层，singleton
 */
@Service
public class ProductService {

    @Setter
    @Autowired
    private ProductRepository productRepository;

    public String getProduct() {

        return productRepository.getProduct();
    }
}
