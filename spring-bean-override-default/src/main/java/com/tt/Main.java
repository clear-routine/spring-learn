package com.tt;

import com.tt.user.User1;
import com.tt.user.User2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 覆盖示例 - 场景一：默认允许 Bean 覆盖
 * 
 * 使用 XML 配置文件定义两个同名的 user Bean：
 * - 第一个：User1 类（在 user1-config.xml 中定义）
 * - 第二个：User2 类（在 user2-config.xml 中定义，会覆盖第一个）
 * 
 * 在 XML 中，后加载的配置文件中的 Bean 会覆盖前面加载的同名 Bean
 * 默认情况下，Spring 允许 Bean 覆盖
 * Spring 会在日志中打印 DEBUG 级别的提醒
 */
public class Main {

    public static void main(String[] args) {
        
        // user1-config.xml 定义第一个 user Bean（User1）
        // user2-config.xml 定义第二个 user Bean（User2，同名，会覆盖第一个）
        // 后加载的配置文件中的 Bean 会覆盖前面加载的同名 Bean
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "user1-config.xml",
                "user2-config.xml"
        );
        
        // 获取 user Bean（实际类型是 User2，因为它在后加载的配置文件中定义）
        Object userBean = context.getBean("user");
        
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
    }
}
