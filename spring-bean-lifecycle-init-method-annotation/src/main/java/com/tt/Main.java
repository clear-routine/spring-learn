package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 init-method 和 destroy-method 生命周期回调（Java 配置方式）。
 * <p>
 * 通过 @Bean(initMethod = "...", destroyMethod = "...") 指定初始化和销毁方法。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== init-method / destroy-method 演示（Java 配置） ==========");
        System.out.println();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> 容器启动完成，UserService 已创建并完成初始化");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 destroyMethod 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
