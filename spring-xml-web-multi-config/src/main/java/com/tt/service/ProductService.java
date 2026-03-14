package com.tt.service;

import com.tt.repository.ProductRepository;
import lombok.Setter;

/**
 * 产品服务层：处理产品相关的业务逻辑
 */
public class ProductService {

    @Setter
    private ProductRepository productRepository;

    /**
     * 获取产品信息
     * @return 产品信息
     */
    public String getProduct() {

        return productRepository.getProduct();
    }
}
