package com.tt;

import com.tt.user.User1;
import com.tt.user.User2;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Bean 覆盖示例 - 场景三：显式允许 Bean 覆盖（不打印日志）
 * 
 * 使用 XML 配置文件定义两个同名的 user Bean：
 * - 第一个：User1 类（在 user1-config.xml 中定义）
 * - 第二个：User2 类（在 user2-config.xml 中定义，会覆盖第一个）
 * 
 * 通过 setAllowBeanDefinitionOverriding(true) 显式允许 Bean 覆盖
 * 这样既允许覆盖，也不会再打印 DEBUG 日志提醒
 */
public class Main {

    public static void main(String[] args) {

        // 使用 GenericXmlApplicationContext 可以设置 Bean 覆盖策略
        // 通过 setAllowBeanDefinitionOverriding(true) 显式允许 Bean 覆盖
        // 这样既允许覆盖，也不会再打印 DEBUG 日志提醒
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setAllowBeanDefinitionOverriding(true);  // 显式允许 Bean 覆盖
        context.load("user1-config.xml", "user2-config.xml");
        context.refresh();
        
        // 获取 user Bean（实际类型是 User2，因为它在后加载的配置文件中定义）
        Object userBean = context.getBean("user");
        System.out.println("获取到的 Bean 类型: " + userBean.getClass().getSimpleName());
        
        // 根据实际类型调用方法
        if (userBean instanceof User1) {
            User1 user = (User1) userBean;
            System.out.println("最终获取到的 Bean: " + user.getName());
            user.process();
        } else if (userBean instanceof User2) {
            User2 user = (User2) userBean;
            System.out.println("最终获取到的 Bean: " + user.getName());
            user.process();
        }
        
        System.out.println("✓ 覆盖成功！后加载的 user2-config.xml 中的 User2 Bean 覆盖了先加载的 user1-config.xml 中的 User1 Bean");
    }
}
