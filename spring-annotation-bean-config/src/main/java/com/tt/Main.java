package com.tt;

import com.tt.config.AppConfig;
import com.tt.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main - 应用程序入口
 *
 * 使用 AnnotationConfigApplicationContext 加载基于 Java Config 的配置类
 * 与 XML 配置方式（ClassPathXmlApplicationContext）不同，这里使用注解配置方式
 *
 * 本模块使用 @Bean 方式手动注册 Bean，而不是 @ComponentScan 自动扫描
 */
public class Main {

    public static void main(String[] args) {

        // 通过 AnnotationConfigApplicationContext 加载配置类
        // AppConfig 类中定义了所有 @Bean 方法，Spring 会执行这些方法并注册 Bean
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 从 Spring 容器中获取 UserController Bean
        // Spring 会自动处理依赖关系，确保 UserController 中的 UserService 已被注入
        UserController controller =
                context.getBean(UserController.class);

        // 调用业务方法
        controller.handle();
    }
}
