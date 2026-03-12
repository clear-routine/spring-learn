package com.tt.service;

import com.tt.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 演示多个生命周期机制指向同一方法时，Spring 只执行一次，不重复调用。
 * <p>
 * afterPropertiesSet() 被三处配置：@PostConstruct + InitializingBean + init-method="afterPropertiesSet"。
 * destroy() 被三处配置：@PreDestroy + DisposableBean + destroy-method="destroy"。
 * 实际各仅执行一次。
 */
public class UserService implements InitializingBean, DisposableBean {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    /**
     * 三个机制都指向 afterPropertiesSet()：
     * 1. @PostConstruct
     * 2. InitializingBean.afterPropertiesSet()
     * 3. init-method="afterPropertiesSet"
     */
    @PostConstruct
    @Override
    public void afterPropertiesSet() {

        System.out.println(">>> [afterPropertiesSet] 被调用（仅执行一次，不重复）");
    }

    /**
     * 三个机制都指向 destroy()：
     * 1. @PreDestroy
     * 2. DisposableBean.destroy() 即本方法
     * 3. destroy-method="destroy"
     */
    @PreDestroy
    @Override
    public void destroy() {

        System.out.println(">>> [destroy] 被调用（仅执行一次，不重复）");
    }
}
