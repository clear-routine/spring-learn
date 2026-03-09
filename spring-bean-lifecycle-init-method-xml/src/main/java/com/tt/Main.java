package com.tt;

import com.tt.controller.UserController;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 init-method 和 destroy-method 生命周期回调（XML 配置方式）。
 * <p>
 * 不依赖 Spring 接口，不使用注解，通过 XML 配置指定初始化和销毁方法。
 * <p>
 * 为何需手动关闭容器：standalone 的 main() 程序由我们创建并持有 ApplicationContext，
 * JVM 退出时不会自动调用 close()。只有执行 close() 或 registerShutdownHook()，
 * Spring 才会触发 destroy-method。Web 应用则由 Servlet 容器负责关闭，无需手动处理。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== init-method / destroy-method 演示（XML） ==========");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println(">>> 容器启动完成，UserService 已创建并完成初始化");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 destroy-method 调用...");
        // 关闭容器，触发 destroy-method；也可用 registerShutdownHook() 在 JVM 退出时自动关闭
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
