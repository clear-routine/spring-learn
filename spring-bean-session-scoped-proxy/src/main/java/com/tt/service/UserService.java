package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * 用户服务层，scope="session"
 *
 * 每个 HTTP Session 一个实例，UserController (singleton) 注入的是代理
 */
@Getter
@Setter
public class UserService {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }
}
