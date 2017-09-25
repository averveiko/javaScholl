package ru.sbt.averveyko.threadpool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {
    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = false;

    private final int maxThreadCount;

    public FixedThreadPool(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    @Override
    public void start() {
        isRunning = true;
        for (int i = 0; i < maxThreadCount; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        workQueue.offer(runnable);
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = workQueue.poll();
                if (nextTask != null) {
                    nextTask.run();
                }
                Thread.yield();
            }
        }
    }
}
