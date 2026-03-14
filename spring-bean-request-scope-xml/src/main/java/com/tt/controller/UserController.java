package com.tt.controller;

import com.tt.service.UserService;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户控制器，scope="request"：每个请求创建新实例，请求间互不干扰
 */
@Getter
public class UserController {

    private static final AtomicInteger instanceCounter = new AtomicInteger(0);

    private final int instanceId = instanceCounter.incrementAndGet();

    @Setter
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
