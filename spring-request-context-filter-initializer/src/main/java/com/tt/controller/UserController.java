package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户控制器：处理用户相关的业务逻辑
 */
@Controller
public class UserController {

    @Setter
    @Autowired
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
