package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * UserService 实现类，scope="session"
 *
 * 使用 JDK 动态代理时，必须实现接口，Spring 生成的 Proxy 实现相同接口
 */
@Getter
@Setter
public class DefaultUserService implements UserService {

    private final String instanceId = UUID.randomUUID().toString().substring(0, 8);

    private UserRepository userRepository;

    @Override
    public String getUser() {

        return userRepository.getUser();
    }
}
