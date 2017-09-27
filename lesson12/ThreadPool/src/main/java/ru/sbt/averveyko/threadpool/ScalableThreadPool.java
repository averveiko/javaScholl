package ru.sbt.averveyko.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final int minThreadCount;
    private final int maxThreadCount;
    private final ArrayList<PoolWorker> threads;
    private final Queue<Runnable> queue;
    private ScalableUtil scalableUtil;

    private boolean isRunning = false;

    public ScalableThreadPool(int min, int max) {
        this.minThreadCount = min;
        this.maxThreadCount = max;

        threads = new ArrayList<>(max);
        queue = new LinkedList<>();

        for (int i = 0; i < minThreadCount; i++) {
            threads.add(new PoolWorker());
            System.err.println("add new thread " + threads.get(threads.size() - 1).getName());
        }

        scalableUtil = new ScalableUtil();

    }

    @Override
    public void start() {
        for (PoolWorker thread : threads)
            thread.start();

        isRunning = true;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
            queue.notify();
        }
        scalableUtil.checkThreadCount();
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;

            while (! Thread.interrupted()) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            scalableUtil.checkThreadCount();
                            queue.wait();

                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    r = queue.remove();
                }
                r.run();
            }
        }
    }

    private class ScalableUtil  {
        private void checkThreadCount() {
            synchronized (queue) {
                if (queue.size() > threads.size() && threads.size() < maxThreadCount) {
                    threads.add(new PoolWorker());
                    System.err.println("add extend thread " + threads.get(threads.size() - 1).getName());

                    if (isRunning)
                        threads.get(threads.size() - 1).start();
                }
                if (queue.size() < threads.size() && threads.size() > minThreadCount) {
                    System.err.println("remove extend thread " + threads.get(threads.size() - 1).getName());
                    threads.get(threads.size() - 1).interrupt();
                    threads.remove(threads.size() - 1);
                }
                //System.err.println("[Current threads count: " + threads.size() + "]");
            }
        }
    }
}
