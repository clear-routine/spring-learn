package com.tt.controller;

import com.tt.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户控制器，@RequestScope：每个请求创建新实例，请求间互不干扰
 */
@Getter
@Controller
@RequestScope
public class UserController {

    private static final AtomicInteger instanceCounter = new AtomicInteger(0);

    private final int instanceId = instanceCounter.incrementAndGet();

    @Setter
    @Autowired
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
