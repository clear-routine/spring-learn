package com.tt.service;

import com.tt.repository.UserRepository;

/**
 * UserService - 用户业务逻辑层
 *
 * 注意：这个类没有使用任何 Spring 注解（如 @Service）
 * 它将在容器外部创建，然后通过 registerSingleton 或 registerBeanDefinition 手动注册到 Spring 容器中
 */
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void process() {
        System.out.println("UserService: " + userRepository.getUser());
    }
}
