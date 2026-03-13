package com.tt;

import org.springframework.context.SmartLifecycle;

/**
 * phase = -10，最先启动、最后停止。
 */
public class LoggerComponent implements SmartLifecycle {

    private volatile boolean running;

    @Override
    public int getPhase() {

        return -10;
    }

    @Override
    public void start() {

        System.out.println(">>> [LoggerComponent] start() phase=-10（最先启动）");
        this.running = true;
    }

    @Override
    public void stop() {

        System.out.println(">>> [LoggerComponent] stop() phase=-10（最后停止）");
        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
