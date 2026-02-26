package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 Spring 延迟加载（lazy initialization）效果
 *
 * 1. XML 方式：lazy-init="true"
 * 2. Java 配置方式：@Lazy
 * 3. 依赖关系会迫使延迟 Bean 提前创建
 *
 * 观察：eagerBean 在容器启动时创建，lazyBean 在首次 getBean 时才创建
 */
public class Main {

    public static void main(String[] args) {
        demoXml();
        demoJavaConfig();
        demoDependencyForcesEager();
    }

    /** XML 配置：lazy-init 效果 */
    private static void demoXml() {
        System.out.println("========== XML 配置：lazy-init ==========");
        System.out.println();

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println();
        System.out.println(">>> 容器启动完成，此时 lazyBean 尚未创建");
        System.out.println();

        System.out.println(">>> 首次调用 getBean(\"lazyBean\")...");
        HeavyBean lazyBean = context.getBean("lazyBean", HeavyBean.class);
        System.out.println(">>> 获取到 lazyBean: " + lazyBean.getName());
        System.out.println();
    }

    /** Java 配置：@Lazy 效果 */
    private static void demoJavaConfig() {
        System.out.println("========== Java 配置：@Lazy ==========");
        System.out.println();
        System.out.println("创建 AnnotationConfigApplicationContext（加载 AppConfig）...");
        System.out.println();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println();
        System.out.println(">>> 容器启动完成，此时 lazyBean 尚未创建");
        System.out.println();

        System.out.println(">>> 首次调用 getBean(\"lazyBean\")...");
        HeavyBean lazyBean = context.getBean("lazyBean", HeavyBean.class);
        System.out.println(">>> 获取到 lazyBean: " + lazyBean.getName());
        System.out.println();
    }

    /** 非延迟 Bean 依赖延迟 Bean 时，延迟 Bean 会被迫在启动时创建 */
    private static void demoDependencyForcesEager() {
        System.out.println("========== 依赖关系迫使延迟 Bean 提前创建 ==========");
        System.out.println();
        System.out.println("eagerBean 依赖 lazyBean（lazy-init=true）");
        System.out.println("加载 dependency-demo.xml...");
        System.out.println();

        ApplicationContext context = new ClassPathXmlApplicationContext("dependency-demo.xml");

        System.out.println();
        System.out.println(">>> 即使 lazyService 标记了 lazy-init，因被 eagerBean 依赖，启动时仍被创建");
    }
}
