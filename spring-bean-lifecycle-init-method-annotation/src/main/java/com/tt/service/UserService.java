package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * UserService 通过 @Bean(initMethod, destroyMethod) 配置生命周期回调。
 * <p>
 * 不实现 Spring 接口，不使用注解，Bean 保持纯粹的 POJO。
 * 执行流程：创建 Bean → 依赖注入 → 执行 initMethod → Bean 可被使用 →
 * 容器关闭 → 执行 destroyMethod
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /** 初始化方法，由 @Bean(initMethod) 指定 */
    public void init() {

        System.out.println(">>> [UserService] initMethod 被调用（依赖注入已完成，Bean 即将可用）");
    }

    /** 销毁方法，由 @Bean(destroyMethod) 指定 */
    public void destroy() {

        System.out.println(">>> [UserService] destroyMethod 被调用（容器关闭，Bean 即将销毁）");
    }
}
