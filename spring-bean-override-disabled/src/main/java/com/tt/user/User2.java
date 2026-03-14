package com.tt.user;

import lombok.Data;

/**
 * 第二个 User 类
 */
@Data
public class User2 {

    private String name = "第二个User";

    public void process() {

        System.out.println("User2[" + name + "]: 处理用户请求");
    }
}
