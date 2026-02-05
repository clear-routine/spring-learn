package com.tt.service;

import com.tt.repository.UserRepository;

public class UserService {
    
    private UserRepository userRepository;
    
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void createUser(Long id) {
        System.out.println("UserService: 创建用户");
        userRepository.save();
    }
    
    public void getUser(Long id) {
        System.out.println("UserService: 获取用户信息");
        userRepository.findById(id);
    }
}
