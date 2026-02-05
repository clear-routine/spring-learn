package com.tt.service;

import com.tt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService - 用户业务逻辑层
 * 
 * @Service 注解：
 * - 标识这是一个业务逻辑层的组件
 * - 会被 Spring 容器自动扫描并注册为 Bean
 * - 通常用于表示应用中的业务逻辑层
 * 
 * @Autowired 注解：
 * - 用于自动注入依赖的 Bean
 * - Spring 会自动查找匹配类型的 Bean 并注入
 * - 可以标注在字段、构造器或 setter 方法上
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void process() {
        System.out.println("UserService: " + userRepository.getUser());
    }
}
