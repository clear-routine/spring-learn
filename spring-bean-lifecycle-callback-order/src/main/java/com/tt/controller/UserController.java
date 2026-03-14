package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

public class UserController {

    @Setter
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
