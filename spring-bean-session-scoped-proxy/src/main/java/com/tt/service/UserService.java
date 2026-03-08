package com.tt.service;

import com.tt.repository.UserRepository;

import java.util.UUID;

/**
 * 用户服务层，scope="session"
 *
 * 每个 HTTP Session 一个实例，UserController (singleton) 注入的是代理
 */
public class UserService {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    public String getInstanceId() {

        return instanceId;
    }
}
