package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 "" 与 null 的区别
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        ExampleBean beanWithEmpty = context.getBean("beanWithEmpty", ExampleBean.class);
        ExampleBean beanWithNull = context.getBean("beanWithNull", ExampleBean.class);

        System.out.println("=== value=\"\"（空字符串）===");
        System.out.println("email == null: " + (beanWithEmpty.getEmail() == null));
        System.out.println("email.equals(\"\"): " + beanWithEmpty.getEmail().equals(""));
        System.out.println("email.length(): " + beanWithEmpty.getEmailLength());
        System.out.println();

        System.out.println("=== <null/>（Java null）===");
        System.out.println("email == null: " + (beanWithNull.getEmail() == null));
        System.out.println("email.length(): ");
        try {
            System.out.println(beanWithNull.getEmailLength());
        } catch (NullPointerException e) {
            System.out.println("  NullPointerException（符合预期）");
        }
    }
}
