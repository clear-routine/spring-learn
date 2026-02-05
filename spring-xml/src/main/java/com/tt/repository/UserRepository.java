package com.tt.repository;

public class UserRepository {
    
    public void save() {
        System.out.println("UserRepository: 保存用户信息");
    }
    
    public void findById(Long id) {
        System.out.println("UserRepository: 根据ID查找用户，ID = " + id);
    }
}
