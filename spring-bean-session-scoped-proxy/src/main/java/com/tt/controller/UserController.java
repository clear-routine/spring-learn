package com.tt.controller;

import com.tt.service.UserService;

/**
 * 用户控制器，singleton
 *
 * 注入 userService（session）的代理，每次调用时代理从当前 session 获取真实实例
 */
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }

    public String getUserServiceInstanceId() {

        return userService.getInstanceId();
    }
}
