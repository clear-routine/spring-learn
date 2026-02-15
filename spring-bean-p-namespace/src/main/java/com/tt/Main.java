package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * p-namespace 演示：Setter 注入的简洁写法
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        User userByProperty = context.getBean("userByProperty", User.class);
        User user = context.getBean("user", User.class);

        System.out.println("常规 property: " + userByProperty);
        System.out.println("p-namespace:   " + user);
    }
}
