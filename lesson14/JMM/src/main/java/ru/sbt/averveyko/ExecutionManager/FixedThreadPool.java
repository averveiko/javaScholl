package ru.sbt.averveyko.ExecutionManager;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool{
    private final int threadCount;
    private volatile int sleepThreadCount;
    private volatile int taskCount = 0;
    private volatile int failedTaskCount = 0;
    private volatile int interruptedTaskCount = 0;

    private final PoolWorker[] threads;
    private final Queue<Runnable> queue;


    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        queue = new LinkedList<>();
        threads = new PoolWorker[threadCount];
        sleepThreadCount = threadCount;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
            System.err.println(threads[i].getName() + " started..");
            sleepThreadCount--;
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
            taskCount++;
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;

            while(true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.err.println(this.getName() + " is waiting...");
                            sleepThreadCount++;
                            System.err.println("sleep threads: " + sleepThreadCount);
                            queue.wait();
                        } catch (InterruptedException ignore) {
                        }
                    }
                    r = queue.remove();
                }
                try {
                    r.run();
                } catch (Exception e) {
                    failedTaskCount++;
                }

            }
        }
    }

    //Context methods
    @Override
    public int getCompletedTaskCount() {
        return taskCount - failedTaskCount - (threadCount - sleepThreadCount);
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }

    @Override
    public boolean isFinished() {
       return sleepThreadCount == threadCount;
    }

    @Override
    public void interrupt() {
        synchronized (queue) {
            if (!queue.isEmpty()) {
                interruptedTaskCount = queue.size();
                queue.clear();
            }
        }
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }
}
