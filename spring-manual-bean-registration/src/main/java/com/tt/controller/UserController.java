package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

/**
 * UserController - 用户控制层
 *
 * 注意：这个类没有使用任何 Spring 注解（如 @Controller）
 * 它将在容器外部创建，然后通过 registerSingleton 或 registerBeanDefinition 手动注册到 Spring 容器中
 */
public class UserController {

    @Setter
    private UserService userService;

    public void handle() {

        userService.process();
    }
}
