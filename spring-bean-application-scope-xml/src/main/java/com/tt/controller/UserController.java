package com.tt.controller;

import com.tt.service.UserService;

import java.util.UUID;

/**
 * 用户控制器，scope="application"：整个 Web 应用一份，所有用户共享
 */
public class UserController {

    private final String instanceId = UUID.randomUUID().toString();

    private int visitCount;

    private UserService userService;

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }

    public void incrementVisit() {

        visitCount++;
    }

    public int getVisitCount() {

        return visitCount;
    }

    public String getInstanceId() {

        return instanceId;
    }
}
