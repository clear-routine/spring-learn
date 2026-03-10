package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;
import com.tt.task.DataInitializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {

        return new UserRepository();
    }

    @Bean
    public UserService userService() {

        UserService userService = new UserService();
        userService.setUserRepository(userRepository());
        return userService;
    }

    @Bean
    public UserController userController() {

        UserController userController = new UserController();
        userController.setUserService(userService());
        return userController;
    }

    /**
     * DataInitializer 实现 SmartInitializingSingleton，
     * 其 afterSingletonsInstantiated() 会在 userRepository、userService、userController 等
     * 所有单例创建完成后执行，用于执行较重的初始化逻辑。
     */
    @Bean
    public DataInitializer dataInitializer() {

        return new DataInitializer();
    }
}
