package com.tt.config;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * UserRepository 有 close()，Spring 自动推断为销毁方法，无需 destroyMethod。
     */
    @Bean
    public UserRepository userRepository() {

        return new UserRepository();
    }

    /**
     * UserService 有 shutdown()，Spring 自动推断为销毁方法，无需 destroyMethod。
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
