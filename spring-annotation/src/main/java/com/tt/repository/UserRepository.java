package com.tt.repository;

import org.springframework.stereotype.Repository;

/**
 * UserRepository - 用户数据访问层
 * 
 * @Repository 注解：
 * - 标识这是一个数据访问层的组件
 * - 会被 Spring 容器自动扫描并注册为 Bean
 * - 通常用于数据库操作相关的代码
 */
@Repository
public class UserRepository {
    public String getUser() {
        return "TT";
    }
}
