package com.tt.product.repository;

import org.springframework.stereotype.Repository;

/**
 * 产品数据访问层
 */
@Repository
public class ProductRepository {

    public String getProduct() {

        return "iPhone 15 Pro";
    }
}
