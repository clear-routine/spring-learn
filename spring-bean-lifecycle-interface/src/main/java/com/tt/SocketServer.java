package com.tt;

import org.springframework.context.Lifecycle;

/**
 * 另一个实现 Lifecycle 的组件，演示容器级联调用多个 Lifecycle Bean。
 */
public class SocketServer implements Lifecycle {

    private boolean running;

    @Override
    public void start() {

        System.out.println(">>> [SocketServer] start() 被调用");
        this.running = true;
    }

    @Override
    public void stop() {

        System.out.println(">>> [SocketServer] stop() 被调用");
        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
