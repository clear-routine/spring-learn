package com.tt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 链式属性注入：property name="fred.bob.sammy" 等价于 getFred().getBob().setSammy("123")
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        ThingOne something = context.getBean("something", ThingOne.class);
        System.out.println(something);
        System.out.println("fred.bob.sammy = " + something.getFred().getBob().getSammy());
    }
}
