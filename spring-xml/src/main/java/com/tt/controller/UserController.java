package com.tt.controller;

import com.tt.service.UserService;

public class UserController {
    
    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public void handle() {
        System.out.println("UserController: 处理用户请求");
        userService.createUser(1L);
        userService.getUser(1L);
    }
}
