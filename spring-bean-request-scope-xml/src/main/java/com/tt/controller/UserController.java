package com.tt.controller;

import com.tt.service.UserService;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户控制器，scope="request"：每个请求创建新实例，请求间互不干扰
 */
public class UserController {

    private static final AtomicInteger instanceCounter = new AtomicInteger(0);

    private final int instanceId = instanceCounter.incrementAndGet();

    private UserService userService;

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }

    /** 用于验证 request scope：每次请求实例 ID 不同 */
    public int getInstanceId() {

        return instanceId;
    }
}
