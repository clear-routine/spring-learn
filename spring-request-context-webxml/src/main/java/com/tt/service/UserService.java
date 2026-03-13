package com.tt.service;

import com.tt.repository.UserRepository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户服务层：处理用户相关的业务逻辑
 *
 * 演示 RequestContextHolder：Service 层无需注入 HttpServletRequest，
 * 即可通过 RequestContextHolder 获取当前请求（需配置 RequestContextListener）。
 */
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        String user = userRepository.getUser();
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs instanceof ServletRequestAttributes servletAttrs) {
            HttpServletRequest request = servletAttrs.getRequest();
            return user + " (请求: " + request.getRequestURI() + ")";
        }
        return user;
    }
}
