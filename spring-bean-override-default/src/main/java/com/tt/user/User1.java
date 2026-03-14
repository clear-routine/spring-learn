package com.tt.user;

import lombok.Data;

/**
 * 第一个 User 类
 */
@Data
public class User1 {

    private String name = "第一个User";

    public void process() {

        System.out.println("User1[" + name + "]: 处理用户请求");
    }
}
