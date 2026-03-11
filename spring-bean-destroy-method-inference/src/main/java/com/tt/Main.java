package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 Spring 自动推断 Bean 的销毁方法。
 * <p>
 * UserRepository 有 close()，UserService 有 shutdown()，均未显式配置 destroyMethod。
 * Spring 会在容器关闭时自动调用这些方法。
 * <p>
 * 执行流程：容器启动 → Bean 创建 → 调用 getUser() → 容器关闭 → 自动调用 close() / shutdown()
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== destroy-method 自动推断演示 ==========");
        System.out.println();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> 容器启动完成");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 Spring 自动推断的 close() / shutdown() 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
