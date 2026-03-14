package com.tt.service;

import com.tt.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * UserService 实现 InitializingBean 和 DisposableBean，演示 Bean 生命周期钩子。
 * <p>
 * 执行流程：
 * 1. 创建 Bean → 依赖注入 → 调用 afterPropertiesSet() → Bean 可被使用
 * 2. 容器关闭 → 调用 destroy() → Bean 销毁
 */
public class UserService implements InitializingBean, DisposableBean {

    @Setter
    private UserRepository userRepository;

    public String getUser() {

        return userRepository.getUser();
    }

    /**
     * InitializingBean 接口方法。Spring 在完成依赖注入后调用，可用于自定义初始化逻辑。
     */
    @Override
    public void afterPropertiesSet() {

        System.out.println(">>> [UserService] afterPropertiesSet() 被调用（依赖注入已完成，Bean 即将可用）");
    }

    /**
     * DisposableBean 接口方法。Spring 在容器关闭、Bean 即将销毁时调用，可用于资源释放等清理逻辑。
     */
    @Override
    public void destroy() {

        System.out.println(">>> [UserService] destroy() 被调用（容器关闭，Bean 即将销毁）");
    }
}
