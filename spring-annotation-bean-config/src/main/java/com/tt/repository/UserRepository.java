package com.tt.repository;

/**
 * UserRepository - 用户数据访问层
 *
 * 在 Java Config 的 @Bean 方式中：
 * - 不需要使用 @Repository 注解
 * - 不需要使用任何 Spring 注解
 * - 这是一个普通的 POJO 类
 * - Bean 的注册由 AppConfig 中的 @Bean 方法完成
 */
public class UserRepository {
    public String getUser() {
        return "TT";
    }
}
