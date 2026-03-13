package com.tt;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 Phased 的启动/停止顺序。
 * <p>
 * phase 小的先启动、后停止；phase 大的后启动、先停止。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== SmartLifecycle 完整演示 ==========");
        System.out.println();
        System.out.println(">>> 1. Phased 启动顺序（phase 小→大）：Logger(-10) → Database(0) → Consumer(10)");
        System.out.println();

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println();
        context.start();
        System.out.println();
        System.out.println(">>> 2. context.start() 完成");
        System.out.println();

        LoggerComponent logger = context.getBean(LoggerComponent.class);
        MessageConsumer consumer = context.getBean(MessageConsumer.class);
        System.out.println(">>> 3. Logger.isRunning() = " + logger.isRunning() + ", Consumer.isRunning() = " + consumer.isRunning());
        System.out.println();

        System.out.println(">>> 4. Phased 停止顺序（phase 大→小）：Consumer(10) → Database(0) → Logger(-10)");
        System.out.println();

        context.stop();

        System.out.println();
        context.close();
        System.out.println(">>> 演示结束");
    }
}
