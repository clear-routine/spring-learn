package com.tt.repository;

/**
 * UserRepository 使用不同的方法名 setup() 和 cleanup()。
 * <p>
 * 单个 Bean 上显式指定 init-method / destroy-method 可覆盖 default-init-method / default-destroy-method。
 */
public class UserRepository {

    public String getUser() {

        return "TT";
    }

    /** 显式指定 init-method="setup" 覆盖默认 */
    public void setup() {

        System.out.println(">>> [UserRepository] setup() 被调用（显式 init-method，覆盖 default-init-method）");
    }

    /** 显式指定 destroy-method="cleanup" 覆盖默认 */
    public void cleanup() {

        System.out.println(">>> [UserRepository] cleanup() 被调用（显式 destroy-method，覆盖 default-destroy-method）");
    }
}
