package com.tt.controller;

import com.tt.service.UserService;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * 用户控制器，scope="application"：整个 Web 应用一份，所有用户共享
 */
@Getter
public class UserController {

    private final String instanceId = UUID.randomUUID().toString();

    private int visitCount;

    @Setter
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }

    public void incrementVisit() {

        visitCount++;
    }
}
