package com.tt.service;

import com.tt.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

/**
 * UserService 使用 @PostConstruct 和 @PreDestroy 演示 Bean 生命周期钩子。
 * <p>
 * 相比 InitializingBean、DisposableBean 接口，注解方式不依赖 Spring 特定接口，
 * @PostConstruct 和 @PreDestroy 属于 JSR-250（Java 标准），具有更好的通用性。
 * <p>
 * 执行流程：创建 Bean → 依赖注入 → 执行 @PostConstruct 方法 → Bean 正常使用 →
 * 容器关闭 → 执行 @PreDestroy 方法
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String getUser() {

        return userRepository.getUser();
    }

    /**
     * @PostConstruct：等价于 InitializingBean.afterPropertiesSet()
     * Spring 在完成依赖注入后执行，可用于自定义初始化逻辑。
     */
    @PostConstruct
    public void init() {

        System.out.println(">>> [UserService] @PostConstruct 方法被调用（依赖注入已完成，Bean 即将可用）");
    }

    /**
     * @PreDestroy：等价于 DisposableBean.destroy()
     * Spring 在容器关闭、Bean 即将销毁时执行，可用于资源释放等清理逻辑。
     */
    @PreDestroy
    public void cleanup() {

        System.out.println(">>> [UserService] @PreDestroy 方法被调用（容器关闭，Bean 即将销毁）");
    }
}
