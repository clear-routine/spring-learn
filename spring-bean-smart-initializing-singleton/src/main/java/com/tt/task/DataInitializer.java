package com.tt.task;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * 演示 SmartInitializingSingleton：适合执行较重的初始化任务。
 * <p>
 * afterSingletonsInstantiated() 在所有单例 Bean 创建完成后执行，此时已脱离单例创建锁，
 * 不会阻塞其他 Bean 的创建，比普通 init 方法更安全高效。
 * 仍处于容器启动阶段，应用对外提供服务之前会完成，不会因请求过早到达而出错。
 */
public class DataInitializer implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {

        System.out.println(">>> [DataInitializer] afterSingletonsInstantiated() 开始执行（所有单例 Bean 已创建完成）");

        // 模拟较重初始化：数据库连接、加载大量数据、远程调用等
        System.out.println(">>> 模拟初始化数据库连接...");
        simulateHeavyTask(500);
        System.out.println(">>> 模拟加载缓存数据...");
        simulateHeavyTask(300);
        System.out.println(">>> 模拟扫描配置文件...");
        simulateHeavyTask(200);

        System.out.println(">>> [DataInitializer] 较重初始化任务完成，应用即将可对外提供服务");
    }

    private void simulateHeavyTask(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
