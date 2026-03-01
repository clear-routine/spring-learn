package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 方法注入（@Lookup）演示 - 注解配置
 *
 * 场景：singleton 需要每次调用时都拿到新的 prototype 实例
 *
 * 原理：与 lookup-method 相同，Spring 用 CGLIB 动态生成子类，重写 getPrototypeBean() 方法。
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class, SingletonWithLookup.class);

        System.out.println("========== 注解 @Lookup：每次拿到新 prototype ==========");
        SingletonWithLookup singleton = context.getBean(SingletonWithLookup.class);
        System.out.println("连续调用 3 次 doSomething()：");
        singleton.doSomething();
        singleton.doSomething();
        singleton.doSomething();
    }
}
