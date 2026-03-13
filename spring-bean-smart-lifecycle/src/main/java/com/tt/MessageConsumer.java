package com.tt;

import org.springframework.context.SmartLifecycle;

/**
 * phase = 10，最后启动、最先停止。
 */
public class MessageConsumer implements SmartLifecycle {

    private volatile boolean running;

    @Override
    public int getPhase() {

        return 10;
    }

    @Override
    public void start() {

        System.out.println(">>> [MessageConsumer] start() phase=10（最后启动）");
        this.running = true;
    }

    @Override
    public void stop() {

        System.out.println(">>> [MessageConsumer] stop() phase=10（最先停止）");
        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
