package com.tt;

import com.tt.controller.OrderController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 Spring XML 四种自动装配模式：
 * - no：不自动装配，显式指定 ref
 * - byName：按属性名匹配 Bean id
 * - byType：按属性类型匹配 Bean
 * - constructor：按构造函数参数类型注入
 */
public class Main {

    public static void main(String[] args) {
        demo("no-autowire.xml", "no（默认）");
        demo("by-name.xml", "byName");
        demo("by-type.xml", "byType");
        demo("constructor.xml", "constructor");
    }

    private static void demo(String config, String mode) {
        System.out.println("========== autowire=\"" + mode + "\" ==========");
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        OrderController controller = context.getBean("orderController", OrderController.class);
        controller.handle("order-001");
        System.out.println();
    }
}
