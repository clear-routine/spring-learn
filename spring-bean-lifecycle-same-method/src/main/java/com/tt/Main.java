package com.tt;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示多个生命周期机制指向同一方法时，Spring 只执行一次，不重复调用。
 * <p>
 * 若未去重，init() 和 destroy() 会各被调用多次；
 * 实际输出仅各一次，说明 Spring 会合并同一方法的多次配置。
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========== 多机制指向同一方法：仅执行一次 ==========");
        System.out.println();
        System.out.println(">>> 初始化：afterPropertiesSet() 被 @PostConstruct、InitializingBean、init-method 三处配置");
        System.out.println();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        System.out.println();
        System.out.println(">>> 销毁：destroy() 被 @PreDestroy、DisposableBean、destroy-method 三处配置");
        System.out.println();

        context.close();
        System.out.println();
        System.out.println(">>> 演示结束：init 和 destroy 各仅输出一次");
    }
}
