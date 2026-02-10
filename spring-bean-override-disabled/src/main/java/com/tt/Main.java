package com.tt;

import com.tt.user.User1;
import com.tt.user.User2;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Bean 覆盖示例 - 场景二：禁止 Bean 覆盖
 * 
 * 使用 XML 配置文件定义两个同名的 user Bean：
 * - 第一个：User1 类（在 user1-config.xml 中定义）
 * - 第二个：User2 类（在 user2-config.xml 中定义，同名）
 * 
 * 通过 setAllowBeanDefinitionOverriding(false) 禁止 Bean 覆盖
 * 如果出现同名 Bean，容器启动直接失败并抛异常
 * 这样可以避免配置错误被悄悄吞掉，强制你去改配置
 */
public class Main {

    public static void main(String[] args) {

        try {
            // 使用 GenericXmlApplicationContext 可以设置 Bean 覆盖策略
            // 通过 setAllowBeanDefinitionOverriding(false) 禁止 Bean 覆盖
            // 如果出现同名 Bean，容器启动直接失败并抛异常
            GenericXmlApplicationContext context = new GenericXmlApplicationContext();
            context.setAllowBeanDefinitionOverriding(false);  // 禁止 Bean 覆盖
            context.load("user1-config.xml", "user2-config.xml");
            context.refresh();  // 这里会抛出异常
            
            // 如果执行到这里，说明没有同名 Bean
            Object userBean = context.getBean("user");
            System.out.println("获取到的 Bean 类型: " + userBean.getClass().getSimpleName());
            
            if (userBean instanceof User1) {
                User1 user = (User1) userBean;
                System.out.println("最终获取到的 Bean: " + user.getName());
                user.process();
            } else if (userBean instanceof User2) {
                User2 user = (User2) userBean;
                System.out.println("最终获取到的 Bean: " + user.getName());
                user.process();
            }
        } catch (Exception e) {
            System.out.println("✓ 正确！禁止覆盖后，出现同名 Bean 会直接报错：");
            System.out.println("  异常类型: " + e.getClass().getSimpleName());
            System.out.println("  异常信息: " + e.getMessage());
        }
    }
}
