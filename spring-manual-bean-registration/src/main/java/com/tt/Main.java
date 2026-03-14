package com.tt;

import com.tt.controller.UserController;
import com.tt.repository.UserRepository;
import com.tt.service.UserService;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main - 演示手动注册 Bean 到 Spring 容器
 *
 * 本示例演示了两种在容器外部创建对象并注册到 Spring 容器的方式：
 * 1. registerSingleton(..) - 注册单例对象
 * 2. registerBeanDefinition(..) - 注册 Bean 定义（支持依赖注入）
 *
 * 这些方法通过 ApplicationContext 的 getAutowireCapableBeanFactory() 方法
 * 获取 DefaultListableBeanFactory 来实现
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== 方式一：使用 registerSingleton ==========");
        demonstrateRegisterSingleton();

        System.out.println("\n========== 方式二：使用 registerBeanDefinition ==========");
        demonstrateRegisterBeanDefinition();
    }

    /**
     * 演示使用 registerSingleton 方法注册已创建的对象
     *
     * registerSingleton 适用于：
     * - 已经在容器外部创建好的对象
     * - 不需要 Spring 进行依赖注入的对象
     * - 简单的单例对象注册
     */
    private static void demonstrateRegisterSingleton() {

        // 创建 AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 必须先调用 refresh() 方法，才能使用 BeanFactory
        context.refresh();

        // 获取 BeanFactory，用于手动注册 Bean
        DefaultListableBeanFactory beanFactory =
            (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();

        // 在容器外部创建对象
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();
        UserController userController = new UserController();

        // 手动设置依赖关系（因为 registerSingleton 不会自动注入）
        userService.setUserRepository(userRepository);
        userController.setUserService(userService);

        // 使用 registerSingleton 注册单例对象
        // 参数：beanName - Bean 的名称，singletonObject - 要注册的对象
        beanFactory.registerSingleton("userRepository", userRepository);
        beanFactory.registerSingleton("userService", userService);
        beanFactory.registerSingleton("userController", userController);

        // 从容器中获取 Bean 并使用
        UserController controller = context.getBean("userController", UserController.class);
        controller.handle();
    }

    /**
     * 演示使用 registerBeanDefinition 方法注册 Bean 定义
     *
     * registerBeanDefinition 适用于：
     * - 需要 Spring 进行依赖注入的场景
     * - 需要 Spring 管理 Bean 生命周期的场景
     * - 更灵活的 Bean 注册方式
     */
    private static void demonstrateRegisterBeanDefinition() {

        // 创建 AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 必须先调用 refresh() 方法，才能使用 BeanFactory
        context.refresh();

        // 获取 BeanFactory
        DefaultListableBeanFactory beanFactory =
            (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();

        // 方式一：注册 UserRepository（使用 RootBeanDefinition）
        RootBeanDefinition repositoryDefinition = new RootBeanDefinition(UserRepository.class);
        beanFactory.registerBeanDefinition("userRepository", repositoryDefinition);

        // 方式二：注册 UserService（使用 RootBeanDefinition，并设置属性依赖）
        RootBeanDefinition serviceDefinition = new RootBeanDefinition(UserService.class);
        // 设置属性依赖，使用 RuntimeBeanReference 引用其他 Bean
        // Spring 在创建 UserService 实例时会自动注入 userRepository
        serviceDefinition.getPropertyValues().add("userRepository",
            new RuntimeBeanReference("userRepository"));
        beanFactory.registerBeanDefinition("userService", serviceDefinition);

        // 方式三：注册 UserController（使用 RootBeanDefinition，并设置属性依赖）
        RootBeanDefinition controllerDefinition = new RootBeanDefinition(UserController.class);
        // 设置属性依赖，使用 RuntimeBeanReference 引用其他 Bean
        // Spring 在创建 UserController 实例时会自动注入 userService
        controllerDefinition.getPropertyValues().add("userService",
            new RuntimeBeanReference("userService"));
        beanFactory.registerBeanDefinition("userController", controllerDefinition);

        // 从容器中获取 Bean 并使用
        // 注意：使用 registerBeanDefinition 注册的 Bean，Spring 会自动处理依赖注入
        UserController controller = context.getBean("userController", UserController.class);
        controller.handle();
    }
}
