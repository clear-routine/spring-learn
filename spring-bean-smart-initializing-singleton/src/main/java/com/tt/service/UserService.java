package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }
}
