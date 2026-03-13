package com.tt.service;

import com.tt.repository.UserRepository;

/**
 * UserService - 用户业务逻辑层
 *
 * 在 Java Config 的 @Bean 方式中：
 * - 不需要使用 @Service 注解
 * - 不需要使用 @Autowired 注解进行依赖注入
 * - 依赖注入由 AppConfig 中的 @Bean 方法在创建 Bean 时手动完成
 * - 只需要提供 setter 方法，供配置类调用
 */
public class UserService {

    private UserRepository userRepository;

    /**
     * Setter 方法，用于注入 UserRepository 依赖
     * 在 AppConfig 的 userService() 方法中会被调用
     *
     * @param userRepository UserRepository 实例
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void process() {
        System.out.println("UserService: " + userRepository.getUser());
    }
}
