package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * UserRepository 实现 AutoCloseable，Spring 自动识别 close() 为销毁方法。
     */
    @Bean
    public UserRepository userRepository() {

        return new UserRepository();
    }

    /**
     * UserService 实现 Closeable，Spring 自动识别 close() 为销毁方法。
     */
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
}
