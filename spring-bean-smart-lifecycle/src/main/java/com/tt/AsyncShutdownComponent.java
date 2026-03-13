package com.tt;

import org.springframework.context.SmartLifecycle;

/**
 * 演示 SmartLifecycle.stop(Runnable callback) 的异步关闭。
 * <p>
 * stop 方法必须在自身关闭流程完成后调用 callback.run()，
 * 否则 DefaultLifecycleProcessor 会一直等待直到超时（默认 30 秒）。
 */
public class AsyncShutdownComponent implements SmartLifecycle {

    private volatile boolean running;

    @Override
    public int getPhase() {

        return 5;
    }

    @Override
    public void start() {

        System.out.println(">>> [AsyncShutdownComponent] start() phase=5");
        this.running = true;
    }

    @Override
    public void stop(Runnable callback) {

        System.out.println(">>> [AsyncShutdownComponent] stop() 开始，模拟异步关闭（2 秒）...");
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                this.running = false;
                System.out.println(">>> [AsyncShutdownComponent] 关闭完成，调用 callback.run()");
                callback.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                this.running = false;
                callback.run();
            }
        }).start();
    }

    /**
     * Lifecycle 接口要求实现，Spring 关闭时调用的是 stop(Runnable callback)，此方法作为兜底。
     */
    @Override
    public void stop() {

        this.running = false;
    }

    @Override
    public boolean isRunning() {

        return running;
    }
}
