package com.tt.controller;

import com.tt.service.UserService;

/**
 * UserController 不带 init/destroy 方法。
 * <p>
 * 没有 init() 或 destroy() 的 Bean 不会报错，
 * Spring 只会对存在对应方法的 Bean 进行调用。
 */
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    public String getUser() {

        return userService.getUser();
    }
}
