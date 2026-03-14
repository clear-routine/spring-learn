package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * 用户服务层，singleton
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }
}
