package com.tt.service;

import com.tt.repository.UserRepository;

import java.util.UUID;

/**
 * UserService 实现类，scope="session"
 *
 * 使用 JDK 动态代理时，必须实现接口，Spring 生成的 Proxy 实现相同接口
 */
public class DefaultUserService implements UserService {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public String getUser() {

        return userRepository.getUser();
    }

    @Override
    public String getInstanceId() {

        return instanceId;
    }
}
