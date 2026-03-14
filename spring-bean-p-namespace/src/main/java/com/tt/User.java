package com.tt;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于演示 p-namespace Setter 注入
 */
@Getter
@Setter
public class User {

    private String name;
    private int age;
    private String email;
    private User manager;  // 对象引用，用 p:manager-ref

    @Override
    public String toString() {

        return "User{name='" + name + "', age=" + age + ", email='" + email
                + "', manager=" + (manager != null ? manager.getName() : "null") + "}";
    }
}
