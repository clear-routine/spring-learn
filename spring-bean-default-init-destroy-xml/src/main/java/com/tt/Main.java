package com.tt;

import com.tt.controller.UserController;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 default-init-method 和 default-destroy-method 统一配置生命周期方法。
 * <p>
 * UserService 使用默认的 init()、destroy()，无需在 Bean 上单独配置。
 * UserRepository 方法名为 setup()、cleanup()，通过显式 init-method/destroy-method 覆盖默认。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== default-init-method / default-destroy-method 演示 ==========");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println(">>> 容器启动完成，以上为 init() 调用顺序");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器，观察 destroy() 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
