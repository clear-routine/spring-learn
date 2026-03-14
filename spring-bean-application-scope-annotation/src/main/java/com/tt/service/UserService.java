package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务层，singleton
 */
@Service
public class UserService {

    @Setter
    @Autowired
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }
}
