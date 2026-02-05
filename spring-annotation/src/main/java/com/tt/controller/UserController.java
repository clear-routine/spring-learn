package com.tt.controller;

import com.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * UserController - 用户控制层
 * 
 * @Controller 注解：
 * - 标识这是一个控制层的组件
 * - 会被 Spring 容器自动扫描并注册为 Bean
 * - 通常用于处理 Web 请求（在 Spring MVC 中）
 * 
 * @Autowired 注解：
 * - 用于自动注入依赖的 Bean
 * - Spring 会自动查找匹配类型的 Bean 并注入
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void handle() {
        userService.process();
    }
}
