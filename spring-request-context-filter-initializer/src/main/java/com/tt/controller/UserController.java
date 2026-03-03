package com.tt.controller;

import com.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户控制器：处理用户相关的业务逻辑
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }
}
