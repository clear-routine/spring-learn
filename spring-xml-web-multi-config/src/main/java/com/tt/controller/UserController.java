package com.tt.controller;

import com.tt.service.UserService;

/**
 * 用户控制器：处理用户相关的业务逻辑
 */
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public String getUser() {
        return userService.getUser();
    }
}
