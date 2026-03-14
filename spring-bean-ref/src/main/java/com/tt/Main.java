package com.tt;

import com.tt.controller.OrderController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ref 演示：注入 bean 实例对象（非字符串）
 * - constructor-arg + ref：构造器注入（OrderService）
 * - property + ref：Setter 注入（OrderController）
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        OrderController controller = context.getBean("orderController", OrderController.class);

        System.out.println("orderService 是对象: " + (controller.getOrderService() != null));
        System.out.println();

        controller.handle();
    }
}
