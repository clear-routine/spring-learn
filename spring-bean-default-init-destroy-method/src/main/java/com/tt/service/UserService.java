package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * UserService 带有 init() 和 destroy() 方法。
 * <p>
 * 无需在 <bean> 上配置 init-method 或 destroy-method，
 * 顶层 default-init-method 和 default-destroy-method 会统一生效。
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /** 由 default-init-method="init" 自动识别并调用 */
    public void init() {

        System.out.println(">>> [UserService] init() 被调用（default-init-method）");
    }

    /** 由 default-destroy-method="destroy" 自动识别并调用 */
    public void destroy() {

        System.out.println(">>> [UserService] destroy() 被调用（default-destroy-method）");
    }
}
