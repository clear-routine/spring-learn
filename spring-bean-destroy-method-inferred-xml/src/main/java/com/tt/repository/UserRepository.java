package com.tt.repository;

/**
 * UserRepository 有 close() 方法，但未配置 destroy-method="(inferred)"。
 * <p>
 * XML 配置下默认不会自动推断，因此容器关闭时 close() 不会被调用。
 */
public class UserRepository {

    public String getUser() {

        return "TT";
    }

    /** 未配置 destroy-method，不会被调用 */
    public void close() {

        System.out.println(">>> [UserRepository] close() 被调用（未配置 destroy-method，理论上不会输出）");
    }
}
