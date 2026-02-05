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
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserController controller =
                context.getBean(UserController.class);

        controller.handle();
    }
}
