package com.tt.user;

import lombok.Data;

import org.springframework.stereotype.Component;

/**
 * User1 类 - 使用 @Component 注解（隐式配置）
 * 这种方式通过组件扫描自动注册为名为 "user" 的 Bean
 */
@Data
@Component("user")
public class User1 {

    private String name = "第一个User";

    public void process() {

        System.out.println("User1[" + name + "]: 处理用户请求（来自 @Component）");
    }
}
