package com.tt;

import org.springframework.context.Lifecycle;

/**
 * 实现 Lifecycle 接口的后台组件示例。
 * <p>
 * 容器调用 start() 时启动，调用 stop() 时停止，
 * 由 ApplicationContext.start() / stop() 级联触发。
 */
public class MessageListener implements Lifecycle {

    private boolean running;

    @Override
    public void start() {

        System.out.println(">>> [MessageListener] start() 被调用");
        this.running = true;
    }

    @Override
    public void stop() {

        System.out.println(">>> [MessageListener] stop() 被调用");
        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
