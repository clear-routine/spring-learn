package com.tt.user;

/**
 * 用户 - 由静态工厂方法创建
 */
public class User {

    private final String version;

    public User(String version) {

        this.version = version;
    }

    public void handle() {

        System.out.println("User 处理请求 [version=" + version + "]");
    }
}
