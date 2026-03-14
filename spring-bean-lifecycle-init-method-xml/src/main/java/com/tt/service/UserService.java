package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;

/**
 * UserService 通过 init-method 和 destroy-method 配置生命周期回调。
 * <p>
 * 不实现 Spring 接口，不使用注解，Bean 保持纯粹的 POJO，配置方式更灵活。
 * 执行流程：创建 Bean → 依赖注入 → 执行 init-method → Bean 可被使用 →
 * 容器关闭 → 执行 destroy-method
 */
public class UserService {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /** 初始化方法，由 init-method 指定，在依赖注入完成后执行 */
    public void init() {

        System.out.println(">>> [UserService] init-method 被调用（依赖注入已完成，Bean 即将可用）");
    }

    /** 销毁方法，由 destroy-method 指定，在 Bean 被销毁时执行 */
    public void destroy() {

        System.out.println(">>> [UserService] destroy-method 被调用（容器关闭，Bean 即将销毁）");
    }
}
