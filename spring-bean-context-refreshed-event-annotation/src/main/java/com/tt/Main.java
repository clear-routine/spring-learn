package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 @EventListener(ContextRefreshedEvent.class) 监听容器启动完成事件。
 * <p>
 * finishRefresh() 发布事件后，监听方法同步执行，refresh() 结束后容器才就绪。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== @EventListener(ContextRefreshedEvent) 演示 ==========");
        System.out.println();

        long start = System.currentTimeMillis();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        long cost = System.currentTimeMillis() - start;

        System.out.println();
        System.out.println(">>> 容器启动完成（耗时 " + cost + "ms），可对外提供服务");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
    }
}
