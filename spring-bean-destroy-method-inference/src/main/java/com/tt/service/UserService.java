package com.tt.service;

import com.tt.repository.UserRepository;

/**
 * UserService 实现 shutdown() 方法。
 * <p>
 * Spring 会自动推断：即使未配置 destroy-method，容器关闭时也会调用 shutdown()。
 */
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    /** Spring 自动推断的销毁方法之一，容器关闭时调用 */
    public void shutdown() {

        System.out.println(">>> [UserService] shutdown() 被自动调用（Spring 推断，未显式配置 destroy-method）");
    }
}
