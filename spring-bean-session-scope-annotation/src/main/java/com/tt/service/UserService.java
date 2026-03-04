package com.tt.service;

import com.tt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务层，singleton
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }
}
