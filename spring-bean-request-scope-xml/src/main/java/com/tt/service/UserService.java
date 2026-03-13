package com.tt.service;

import com.tt.repository.UserRepository;

/**
 * 用户服务层，singleton
 */
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }
}
