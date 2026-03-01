package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 验证 Spring 单例作用域（per-container, per-bean）
 *
 * 1. 同一容器、同一 bean 定义：多次 getBean 返回同一实例
 * 2. 同一容器、同一类两个 bean 定义（a1、a2）：各自一个实例
 * 3. 两个容器：每个容器各自一个实例，非 JVM 全局唯一
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("========== 同一 bean 定义，多次 getBean 返回同一实例 ==========");
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        SharedService bean1 = context.getBean("a1", SharedService.class);
        SharedService bean2 = context.getBean("a1", SharedService.class);
        System.out.println("getBean(\"a1\") 两次: " + bean1.getInstanceId() + " vs " + bean2.getInstanceId());
        System.out.println("同一实例: " + (bean1 == bean2));
        System.out.println();

        System.out.println("========== 同一类两个 bean 定义（a1、a2），各自一个实例 ==========");
        SharedService a1 = context.getBean("a1", SharedService.class);
        SharedService a2 = context.getBean("a2", SharedService.class);
        System.out.println("a1: " + a1.getInstanceId() + ", a2: " + a2.getInstanceId());
        System.out.println("不同实例: " + (a1 != a2));
        System.out.println();

        System.out.println("========== 两个容器，各自一个实例（非 JVM 全局唯一） ==========");
        ApplicationContext contextA = new ClassPathXmlApplicationContext("application.xml");
        ApplicationContext contextB = new ClassPathXmlApplicationContext("application.xml");
        SharedService fromA = contextA.getBean("a1", SharedService.class);
        SharedService fromB = contextB.getBean("a1", SharedService.class);
        System.out.println("容器 A 的 a1: " + fromA.getInstanceId());
        System.out.println("容器 B 的 a1: " + fromB.getInstanceId());
        System.out.println("不同实例（不同容器）: " + (fromA != fromB));
        System.out.println();
    }
}
