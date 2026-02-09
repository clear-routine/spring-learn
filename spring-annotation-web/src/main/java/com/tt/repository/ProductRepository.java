package com.tt.repository;

import org.springframework.stereotype.Repository;

/**
 * 产品数据访问层
 * 
 * 使用 @Repository 注解标识当前数据访问层类，使其能够被 Spring 容器统一管理
 */
@Repository
public class ProductRepository {
    
    public String getProduct() {
        return "iPhone 15 Pro";
    }
}
