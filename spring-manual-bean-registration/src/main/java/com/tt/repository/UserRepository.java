package com.tt.repository;

/**
 * UserRepository - 用户数据访问层
 *
 * 注意：这个类没有使用任何 Spring 注解（如 @Repository）
 * 它将在容器外部创建，然后通过 registerSingleton 或 registerBeanDefinition 手动注册到 Spring 容器中
 */
public class UserRepository {

    public String getUser() {
        return "TT";
    }
}
