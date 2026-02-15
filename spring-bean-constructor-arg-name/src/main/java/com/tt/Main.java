package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 验证构造函数参数名 (name) 匹配
 *
 * name 匹配最可读、顺序无关，需 javac -parameters 保留参数名
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        ExampleBean bean = context.getBean("exampleBean", ExampleBean.class);
        System.out.println(bean);
    }
}
