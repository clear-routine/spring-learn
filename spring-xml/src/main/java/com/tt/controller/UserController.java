package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

public class UserController {

    @Setter
    private UserService userService;

    public void handle() {

        System.out.println("UserController: 处理用户请求");
        userService.createUser(1L);
        userService.getUser(1L);
    }
}
