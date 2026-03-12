package com.tt.service;

import com.tt.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 演示同一 Bean 多种生命周期机制的调用顺序。
 * <p>
 * 初始化顺序：1. @PostConstruct → 2. InitializingBean.afterPropertiesSet() → 3. init-method
 * 销毁顺序：1. @PreDestroy → 2. DisposableBean.destroy() → 3. destroy-method
 */
public class UserService implements InitializingBean, DisposableBean {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    /** 1. 最先执行 */
    @PostConstruct
    public void onPostConstruct() {

        System.out.println(">>> [1/3] @PostConstruct 方法被调用");
    }

    /** 2. 接着执行 */
    @Override
    public void afterPropertiesSet() {

        System.out.println(">>> [2/3] InitializingBean.afterPropertiesSet() 被调用");
    }

    /** 3. 最后执行（init-method 指定） */
    public void customInit() {

        System.out.println(">>> [3/3] init-method 自定义初始化方法被调用");
    }

    /** 1. 最先执行 */
    @PreDestroy
    public void onPreDestroy() {

        System.out.println(">>> [1/3] @PreDestroy 方法被调用");
    }

    /** 2. 接着执行 */
    @Override
    public void destroy() {

        System.out.println(">>> [2/3] DisposableBean.destroy() 被调用");
    }

    /** 3. 最后执行（destroy-method 指定） */
    public void customDestroy() {

        System.out.println(">>> [3/3] destroy-method 自定义销毁方法被调用");
    }
}
