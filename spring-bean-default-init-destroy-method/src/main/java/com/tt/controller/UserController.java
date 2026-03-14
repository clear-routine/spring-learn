package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

/**
 * UserController 不带 init/destroy 方法。
 * <p>
 * 没有 init() 或 destroy() 的 Bean 不会报错，
 * Spring 只会对存在对应方法的 Bean 进行调用。
 */
public class UserController {

    @Setter
    private UserService userService;

    public String getUser() {

        return userService.getUser();
    }
}
