package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * c-namespace 演示：构造器注入的简洁写法
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        User byConstructorArg = context.getBean("userByConstructorArg", User.class);
        User byCNamespace = context.getBean("user", User.class);

        System.out.println("constructor-arg: " + byConstructorArg);
        System.out.println("c-namespace:     " + byCNamespace);
    }
}
