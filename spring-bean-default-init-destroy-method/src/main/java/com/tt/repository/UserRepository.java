package com.tt.repository;

/**
 * UserRepository 带有 init() 和 destroy() 方法。
 * <p>
 * 由于 <beans> 配置了 default-init-method 和 default-destroy-method，
 * 容器会自动检测并调用这些方法，无需在 <bean> 上单独指定。
 */
public class UserRepository {

    public String getUser() {

        return "TT";
    }

    /** 由 default-init-method="init" 自动识别并调用 */
    public void init() {

        System.out.println(">>> [UserRepository] init() 被调用（default-init-method）");
    }

    /** 由 default-destroy-method="destroy" 自动识别并调用 */
    public void destroy() {

        System.out.println(">>> [UserRepository] destroy() 被调用（default-destroy-method）");
    }
}
