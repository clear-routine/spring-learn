package com.tt.controller;

import com.tt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户控制器：处理用户相关的业务逻辑
 * 
 * 在控制器类上添加对应的 @Controller 注解，并在属性上使用 @Autowired 注解完成依赖注入，
 * 这样就可以彻底替代基于 XML 配置文件的对象关系管理和配置方式，使整体代码结构更加清晰、简洁
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public String getUser() {
        return userService.getUser();
    }
}
