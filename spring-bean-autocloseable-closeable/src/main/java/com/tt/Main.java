package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 Spring 自动识别 AutoCloseable / Closeable 实现类。
 * <p>
 * UserRepository 实现 AutoCloseable，UserService 实现 Closeable。
 * 均未显式配置 destroyMethod，Spring 在容器关闭时自动调用 close()。
 * <p>
 * 优点：使用 Java 标准接口，代码不依赖 Spring，可脱离 Spring 复用。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== AutoCloseable / Closeable 演示 ==========");
        System.out.println();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> 容器启动完成");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 AutoCloseable / Closeable 的 close() 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
