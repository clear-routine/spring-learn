package com.tt.repository;

/**
 * UserRepository 实现 Java 标准接口 AutoCloseable。
 * <p>
 * Spring 会自动识别，将 close() 视为销毁方法，容器关闭时调用。
 * 使用标准接口，代码不依赖 Spring，可脱离 Spring 复用。
 */
public class UserRepository implements AutoCloseable {

    public String getUser() {

        return "TT";
    }

    @Override
    public void close() {

        System.out.println(">>> [UserRepository] close() 被调用（实现 AutoCloseable，Spring 自动识别）");
    }
}
