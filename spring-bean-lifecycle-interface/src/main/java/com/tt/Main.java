package com.tt;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 context.start() 和 context.stop() 不控制容器本身，只触发 Lifecycle Bean。
 * <p>
 * 关键验证：stop() 之后容器仍存活，仍可 getBean；只有 close() 才真正销毁容器。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== start/stop 不控制容器，只触发 Lifecycle Bean ==========");
        System.out.println();

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(">>> 1. 容器已 refresh，context.isActive() = " + context.isActive());
        System.out.println();

        System.out.println(">>> 2. 调用 context.start()（仅触发 Lifecycle Bean，不启动容器）");
        context.start();
        System.out.println(">>>    context.isActive() = " + context.isActive() + "（容器本就处于活跃状态）");
        System.out.println();

        MessageListener listener = context.getBean(MessageListener.class);
        System.out.println(">>> 3. MessageListener.isRunning() = " + listener.isRunning());
        System.out.println();

        System.out.println(">>> 4. 调用 context.stop()（仅触发 Lifecycle Bean 停止，不销毁容器）");
        context.stop();
        System.out.println(">>>    context.isActive() = " + context.isActive() + "（容器仍然存活）");
        System.out.println();

        System.out.println(">>> 5. stop 之后：仍可 getBean，证明 stop() 未销毁容器");
        context.getBean(MessageListener.class);
        System.out.println(">>>    getBean(MessageListener) 正常");
        System.out.println(">>>    MessageListener.isRunning() = " + listener.isRunning());
        System.out.println();

        System.out.println(">>> 6. 调用 context.close()（才真正销毁容器）");
        context.close();
        System.out.println(">>>    context.isActive() = " + context.isActive());
        System.out.println();
        System.out.println(">>> 演示结束");
    }
}
