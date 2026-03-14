package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * UserService 使用统一命名的 init() 和 destroy()。
 * <p>
 * 通过 default-init-method 和 default-destroy-method，无需在 Bean 上单独配置。
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /** 默认初始化方法，由 default-init-method 指定 */
    public void init() {

        System.out.println(">>> [UserService] init() 被调用（default-init-method，未在 Bean 上单独配置）");
    }

    /** 默认销毁方法，由 default-destroy-method 指定 */
    public void destroy() {

        System.out.println(">>> [UserService] destroy() 被调用（default-destroy-method，未在 Bean 上单独配置）");
    }
}
