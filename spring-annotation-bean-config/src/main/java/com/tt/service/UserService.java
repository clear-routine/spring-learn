package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

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

    @Setter
    private UserRepository userRepository;

    public void process() {

        System.out.println("UserService: " + userRepository.getUser());
    }
}
