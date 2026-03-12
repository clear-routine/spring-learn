package com.tt;

import com.tt.controller.UserController;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 default-init-method 和 default-destroy-method。
 * <p>
 * 在顶层 <beans> 中配置 default-init-method="init"、default-destroy-method="destroy" 后，
 * 所有 Bean 会自动检查是否存在 init()、destroy() 方法并在适当时机调用。
 * 无需在每个 <bean> 上重复配置 init-method、destroy-method，
 * 既减少冗余配置，又统一了生命周期方法的命名规范。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== default-init-method / default-destroy-method 演示 ==========");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println(">>> 容器启动完成");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 destroy() 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 演示结束");
    }
}
