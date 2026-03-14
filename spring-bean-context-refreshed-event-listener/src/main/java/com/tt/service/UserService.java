package com.tt.service;

import com.tt.repository.UserRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;

public class UserService implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {

        System.out.println(">>> [UserService] ContextRefreshedEvent 触发，开始执行初始化任务");

        System.out.println(">>> 模拟数据准备...");
        simulateHeavyTask(400);
        System.out.println(">>> 模拟资源加载...");
        simulateHeavyTask(300);
        System.out.println(">>> 模拟系统检查...");
        simulateHeavyTask(200);

        System.out.println(">>> [UserService] 初始化完成，容器即将就绪");
    }

    public String getUser() {

        return userRepository.getUser();
    }

    private void simulateHeavyTask(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
