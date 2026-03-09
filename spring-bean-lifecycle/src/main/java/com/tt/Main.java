package com.tt;

import com.tt.controller.UserController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 Spring Bean 生命周期钩子：InitializingBean 与 DisposableBean。
 * <p>
 * 流程说明：
 * 1. 创建 Bean → 依赖注入 → afterPropertiesSet() → Bean 可被使用
 * 2. 容器关闭 → destroy() → Bean 销毁
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== Spring Bean 生命周期钩子演示 ==========");
        System.out.println();

        /*
         * 必须使用 ConfigurableApplicationContext 的实现类（如 ClassPathXmlApplicationContext），
         * 因为 ApplicationContext 接口本身没有 close() 和 registerShutdownHook() 方法，
         * 这两个方法定义在子接口 ConfigurableApplicationContext 中，用于正确触发 DisposableBean.destroy()。
         *
         * 其他可选实现：AnnotationConfigApplicationContext（Java 配置）、
         * FileSystemXmlApplicationContext（从文件系统加载 XML）等。
         */
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        // 注册关闭钩子，使 main 退出或 JVM 关闭时自动调用 context.close()，从而触发 DisposableBean.destroy()
        context.registerShutdownHook();

        System.out.println(">>> 容器启动完成，UserService 已创建并完成初始化");
        System.out.println();

        UserController userController = context.getBean("userController", UserController.class);
        String user = userController.getUser();
        System.out.println(">>> 调用 getUser() 返回: " + user);
        System.out.println();

        System.out.println(">>> 即将关闭容器（模拟程序退出），观察 destroy() 调用...");
        context.close();
        System.out.println();
        System.out.println(">>> 容器已关闭，演示结束");
    }
}
