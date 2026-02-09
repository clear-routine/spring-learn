package com.tt.service;

import com.tt.repository.UserRepository;

/**
 * 用户服务层：处理用户相关的业务逻辑
 */
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public String getUser() {
        return userRepository.getUser();
    }
}
