package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * UserService 有 shutdown() 方法，并配置 destroy-method="(inferred)"。
 * <p>
 * Spring 会自动检测 close() 或 shutdown()，容器关闭时调用。
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /** 配置 destroy-method="(inferred)" 后，Spring 自动识别并调用 */
    public void shutdown() {

        System.out.println(">>> [UserService] shutdown() 被调用（destroy-method=\"(inferred)\" 启用自动推断）");
    }
}
