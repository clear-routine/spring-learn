package com.tt;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 Spring 原型作用域（prototype）Bean 的生命周期钩子
 *
 * 1. 前置钩子（@PostConstruct）：每次创建实例时会被 Spring 调用
 * 2. 后置钩子（@PreDestroy）：Spring 不会为原型 Bean 自动调用
 * 3. 自定义后置钩子：通过 PrototypeBeanCleanupProcessor 在容器关闭时统一清理
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("========== 原型作用域 Bean 生命周期演示 ==========\n");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println(">>> 第一次 getBean，创建第一个原型实例：");
        System.out.println();
        PrototypeScopedBean bean1 = context.getBean("prototypeBean", PrototypeScopedBean.class);
        System.out.println("  实例 ID: " + bean1.getInstanceId() + "\n");

        System.out.println(">>> 第二次 getBean，创建第二个实例：");
        System.out.println();
        PrototypeScopedBean bean2 = context.getBean("prototypeBean", PrototypeScopedBean.class);
        System.out.println("  实例 ID: " + bean2.getInstanceId() + "\n");

        System.out.println(">>> 验证：每次 getBean 都是新实例: " + (bean1 != bean2));
        System.out.println();

        System.out.print(">>> 关闭容器，触发自定义后置钩子（PrototypeBeanCleanupProcessor.destroy）：");
        context.close();
        System.out.println("\n>>> 演示完成");
    }
}
