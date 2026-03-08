package com.tt.controller;

import com.tt.service.UserService;

/**
 * 用户控制器，singleton
 *
 * 注入 UserService（接口类型），JDK 代理实现该接口
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
