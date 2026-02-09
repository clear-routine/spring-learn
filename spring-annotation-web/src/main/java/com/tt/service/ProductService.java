package com.tt.service;

import com.tt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品服务层：处理产品相关的业务逻辑
 * 
 * 通过 @Service 注解标识当前类为业务处理类，并配合 @Autowired 注解完成依赖注入，
 * 使该类能够被 Spring 容器统一管理
 */
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
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
