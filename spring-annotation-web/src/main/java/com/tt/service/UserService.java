package com.tt.service;

import com.tt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务层：处理用户相关的业务逻辑
 * 
 * 通过 @Service 注解标识当前类为业务处理类，并配合 @Autowired 注解完成依赖注入，
 * 使该类能够被 Spring 容器统一管理
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
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
