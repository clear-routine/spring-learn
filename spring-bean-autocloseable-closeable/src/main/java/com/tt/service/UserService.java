package com.tt.service;

import com.tt.repository.UserRepository;

import java.io.Closeable;
import java.io.IOException;

/**
 * UserService 实现 Java 标准接口 Closeable。
 * <p>
 * Closeable 继承自 AutoCloseable，Spring 同样会自动识别 close() 为销毁方法。
 * 使用标准接口，代码不依赖 Spring，可脱离 Spring 复用。
 */
public class UserService implements Closeable {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    @Override
    public void close() throws IOException {

        System.out.println(">>> [UserService] close() 被调用（实现 Closeable，Spring 自动识别）");
    }
}
