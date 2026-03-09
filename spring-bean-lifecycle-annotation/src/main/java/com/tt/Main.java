package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示 @PostConstruct 和 @PreDestroy 生命周期钩子（注解方式）。
 * <p>
 * 流程说明：
 * 1. 创建 Bean → 依赖注入 → 执行 @PostConstruct 方法 → Bean 正常使用
 * 2. 容器关闭 → 执行 @PreDestroy 方法 → Bean 销毁
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== @PostConstruct / @PreDestroy 生命周期钩子演示 ==========");
        System.out.println();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> 容器启动完成，UserService 已创建并完成初始化");
        System.out.println();

        UserController userController = context.getBean(UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 @PreDestroy 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
