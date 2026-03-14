package com.tt.controller;

import com.tt.service.UserService;
import lombok.Setter;

/**
 * UserController - 用户控制层
 *
 * 在 Java Config 的 @Bean 方式中：
 * - 不需要使用 @Controller 注解
 * - 不需要使用 @Autowired 注解进行依赖注入
 * - 依赖注入由 AppConfig 中的 @Bean 方法在创建 Bean 时手动完成
 * - 只需要提供 setter 方法，供配置类调用
 */
public class UserController {

    @Setter
    private UserService userService;

    public void handle() {

        userService.process();
    }
}
