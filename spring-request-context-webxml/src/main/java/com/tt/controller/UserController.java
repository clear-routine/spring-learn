package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

/**
 * 用户控制器：处理用户相关的业务逻辑
 */
public class UserController {

    @Setter
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
