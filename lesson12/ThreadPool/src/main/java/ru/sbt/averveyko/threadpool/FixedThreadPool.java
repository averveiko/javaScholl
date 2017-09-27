package ru.sbt.averveyko.threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final int threadCount;
    private final PoolWorker[] threads;
    private final Queue<Runnable> queue;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        queue = new LinkedList<>();
        threads = new PoolWorker[threadCount];
    }

    @Override
    public void start() {
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
            System.out.println(threads[i].getName() + " started..");
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
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
                            System.out.println(this.getName() + " is waiting...");
                            queue.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    r = queue.remove();
                }
                r.run();
            }
        }
    }
}
