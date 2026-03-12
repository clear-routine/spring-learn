package com.tt;

import com.tt.controller.UserController;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示同一 Bean 多种生命周期机制的调用顺序。
 * <p>
 * 初始化顺序：@PostConstruct → InitializingBean.afterPropertiesSet() → init-method
 * 销毁顺序：@PreDestroy → DisposableBean.destroy() → destroy-method
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== 生命周期回调调用顺序演示 ==========");
        System.out.println();
        System.out.println(">>> 初始化阶段（按顺序执行）：");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println();
        System.out.println(">>> 容器启动完成");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        System.out.println(">>> 调用 getUser() 返回: " + userController.getUser());
        System.out.println();
        System.out.println(">>> 销毁阶段（按顺序执行）：");
        System.out.println();

        context.close();
        System.out.println();
        System.out.println(">>> 演示结束");
    }
}
