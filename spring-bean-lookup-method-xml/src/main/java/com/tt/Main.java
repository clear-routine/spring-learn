package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 方法注入（lookup-method）演示 - XML 配置
 *
 * 场景：singleton 需要每次调用时都拿到新的 prototype 实例
 *
 * 原理：Spring 用 CGLIB 动态生成 SingletonBean 的子类，重写 getPrototypeBean() 方法。
 * 每次调用时，执行的是子类的实现，会去容器里重新 getBean，从而拿到新实例。
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println("========== 方法注入（lookup-method）：每次拿到新 prototype ==========");
        SingletonBean singletonBean = context.getBean("singletonBean", SingletonBean.class);
        System.out.println("连续调用 3 次 doSomething()：");
        singletonBean.doSomething();
        singletonBean.doSomething();
        singletonBean.doSomething();

        System.out.println();
        System.out.println("========== 属性注入（错误示范）：始终是同一个 prototype ==========");
        SingletonWithPropertyInjection wrong = context.getBean("singletonWithProperty", SingletonWithPropertyInjection.class);
        System.out.println("连续调用 3 次 doSomething()：");
        wrong.doSomething();
        wrong.doSomething();
        wrong.doSomething();
    }
}
