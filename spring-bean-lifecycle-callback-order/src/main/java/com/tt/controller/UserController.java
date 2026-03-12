package com.tt.controller;

import com.tt.service.UserService;

public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }
}
