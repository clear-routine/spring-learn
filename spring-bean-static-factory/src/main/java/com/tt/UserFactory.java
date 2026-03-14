package com.tt;

import com.tt.user.User;

/**
 * 静态工厂类
 * Spring 调用静态方法，返回值作为 Bean 注册
 * 支持无参和带参重载，容器根据 constructor-arg 选择对应方法
 */
public class UserFactory {

    private UserFactory() {

    }

    /** 无参工厂方法 */
    public static User createInstance() {

        return new User("default");
    }

    /** 带参工厂方法：Spring 根据 constructor-arg 匹配此重载 */
    public static User createInstance(String version) {

        return new User(version);
    }
}
