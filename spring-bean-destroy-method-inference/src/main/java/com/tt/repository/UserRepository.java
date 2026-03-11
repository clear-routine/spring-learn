package com.tt.repository;

/**
 * UserRepository 实现 close() 方法。
 * <p>
 * Spring 会自动推断：即使未配置 destroy-method，容器关闭时也会调用 close()。
 */
public class UserRepository {

    public String getUser() {

        return "TT";
    }

    /** Spring 自动推断的销毁方法之一，容器关闭时调用 */
    public void close() {

        System.out.println(">>> [UserRepository] close() 被自动调用（Spring 推断，未显式配置 destroy-method）");
    }
}
