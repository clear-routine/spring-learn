package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 SmartInitializingSingleton：适合较重初始化任务的执行时机。
 * <p>
 * 执行顺序：创建所有单例 Bean（含依赖注入） → afterSingletonsInstantiated() →
 * 容器启动完成，应用可对外提供服务。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== SmartInitializingSingleton 演示 ==========");
        System.out.println();

        long start = System.currentTimeMillis();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        long cost = System.currentTimeMillis() - start;

        System.out.println();
        System.out.println(">>> 容器启动完成（耗时 " + cost + "ms），UserController 已就绪");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
    }
}
