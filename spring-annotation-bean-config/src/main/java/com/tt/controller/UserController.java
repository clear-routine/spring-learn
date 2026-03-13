package com.tt.controller;

import com.tt.service.UserService;

/**
 * UserController - 用户控制层
 *
 * 在 Java Config 的 @Bean 方式中：
 * - 不需要使用 @Controller 注解
 * - 不需要使用 @Autowired 注解进行依赖注入
 * - 依赖注入由 AppConfig 中的 @Bean 方法在创建 Bean 时手动完成
 * - 只需要提供 setter 方法，供配置类调用
 */
public class UserController {

    private UserService userService;

    /**
     * Setter 方法，用于注入 UserService 依赖
     * 在 AppConfig 的 userController() 方法中会被调用
     *
     * @param userService UserService 实例
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void handle() {
        userService.process();
    }
}
