package com.tt.controller;

import com.tt.service.UserService;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }
}
