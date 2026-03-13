package com.tt;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 SmartLifecycle.stop(Runnable callback) 异步关闭与 lifecycleProcessor 超时。
 */
public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        context.start();

        AsyncShutdownComponent async = context.getBean(AsyncShutdownComponent.class);
        System.out.println(">>> isRunning = " + async.isRunning());

        System.out.println(">>> context.stop()，AsyncShutdownComponent 异步关闭 2 秒后调用 callback.run()");
        context.stop();

        context.close();
        System.out.println(">>> 演示结束");
    }
}
