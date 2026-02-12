package com.tt;

import com.tt.config.AppConfig;
import com.tt.user.User1;
import com.tt.user.User2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 覆盖优先级示例
 * 
 * 演示：使用 @Configuration + @Bean 方式定义的 Bean 会覆盖
 * 通过 @Component 注解定义的同名 Bean
 * 
 * 原因：Spring 遵循"显式 > 隐式"的原则
 * - @Bean 属于显式配置，优先级更高
 * - @Component 属于隐式配置（组件扫描），优先级较低
 * - 即使禁止 Bean 覆盖，@Bean 仍然可以覆盖 @Component
 */
public class Main {

    public static void main(String[] args) {
        
        // - @ComponentScan 会扫描到 User1（@Component("user")）
        // - @Bean("user") 会定义另一个 user Bean（User2）
        // 由于 @Bean 是显式配置，优先级高于 @Component（隐式配置），所以会覆盖
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // 获取 user Bean
        Object userBean = context.getBean("user");

        // 根据实际类型调用方法
        if (userBean instanceof User1) {
            User1 user = (User1) userBean;
            System.out.println("   名称: " + user.getName());
            user.process();
        } else if (userBean instanceof User2) {
            User2 user = (User2) userBean;
            System.out.println("   名称: " + user.getName());
            user.process();
        }
    }
}
