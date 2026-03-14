package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

/**
 * 用户控制器：处理用户相关的业务逻辑
 */
public class UserController {

    @Setter
    private UserService userService;

    /**
     * 处理用户请求
     */
    public void handle() {

        System.out.println("UserController: 处理用户请求");
        String user = userService.getUser();
        System.out.println("UserController: 获取到用户信息 - " + user);
    }
}
