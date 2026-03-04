package com.tt.controller;

import com.tt.service.UserService;

import java.util.UUID;

/**
 * 用户控制器，scope="session"：每个 HTTP Session 创建一个新实例，同会话内多次请求复用同一实例
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

    /** 同 Session 内每次请求累加，用于验证 session scope */
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
