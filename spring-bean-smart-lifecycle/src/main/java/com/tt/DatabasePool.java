package com.tt;

import org.springframework.context.SmartLifecycle;

/**
 * phase = 0，启动顺序在 Logger 之后、Consumer 之前。
 */
public class DatabasePool implements SmartLifecycle {

    private volatile boolean running;

    @Override
    public int getPhase() {

        return 0;
    }

    @Override
    public void start() {

        System.out.println(">>> [DatabasePool] start() phase=0");
        this.running = true;
    }

    @Override
    public void stop() {
        System.out.println(">>> [DatabasePool] stop() phase=0");
        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
