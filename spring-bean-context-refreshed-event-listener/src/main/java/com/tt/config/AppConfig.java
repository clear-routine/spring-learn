package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;

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

}
