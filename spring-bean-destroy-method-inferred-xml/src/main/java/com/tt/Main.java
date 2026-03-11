package com.tt;

import com.tt.controller.UserController;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 XML 中 destroy-method="(inferred)" 自动推断销毁方法。
 * <p>
 * UserService：配置 destroy-method="(inferred)"，有 shutdown() → 容器关闭时调用。
 * UserRepository：未配置 destroy-method，有 close() → 容器关闭时不会调用。
 * <p>
 * 对比两者可见：(inferred) 启用时 Spring 才自动检测 close() 或 shutdown()。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== XML destroy-method=\"(inferred)\" 演示 ==========");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println(">>> 容器启动完成");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察差异：");
        System.out.println(">>> UserService 有 destroy-method=\"(inferred)\" → shutdown() 会被调用");
        System.out.println(">>> UserRepository 未配置 destroy-method → close() 不会被调用");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
