package com.tt.controller;

import com.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户控制器，@RequestScope：每个请求创建新实例，请求间互不干扰
 */
@Controller
@RequestScope
public class UserController {

    private static final AtomicInteger instanceCounter = new AtomicInteger(0);

    private final int instanceId = instanceCounter.incrementAndGet();

    private UserService userService;

    @Autowired
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
