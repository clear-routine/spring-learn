package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * 注册 UserRepository Bean
     *
     * @Bean 注解表示该方法返回的对象会被注册为 Spring 容器中的一个 Bean
     * 方法名 userRepository 会作为 Bean 的默认名称（也可以通过 @Bean("customName") 指定）
     *
     * @return UserRepository 实例
     */
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    /**
     * 注册 UserService Bean
     *
     * 在创建 UserService 时，通过调用 userRepository() 方法获取 UserRepository Bean
     * Spring 会确保 userRepository() 方法只被调用一次，后续调用会返回同一个实例（单例模式）
     *
     * @return UserService 实例，已注入 UserRepository 依赖
     */
    @Bean
    public UserService userService() {
        UserService userService = new UserService();
        // 手动调用 userRepository() 方法获取 Bean 并注入
        userService.setUserRepository(userRepository());
        return userService;
    }

    /**
     * 注册 UserController Bean
     *
     * 在创建 UserController 时，通过调用 userService() 方法获取 UserService Bean
     * 同样，Spring 会确保单例模式，多次调用 userService() 返回同一个实例
     *
     * @return UserController 实例，已注入 UserService 依赖
     */
    @Bean
    public UserController userController() {
        UserController userController = new UserController();
        // 手动调用 userService() 方法获取 Bean 并注入
        userController.setUserService(userService());
        return userController;
    }
}
