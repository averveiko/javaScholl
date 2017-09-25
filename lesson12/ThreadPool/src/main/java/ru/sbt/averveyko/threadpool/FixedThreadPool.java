package ru.sbt.averveyko.threadpool;

public class FixedThreadPool implements ThreadPool {
    private final int maxThreadCount;

    public FixedThreadPool(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    @Override
    public void start() {
        
    }

    @Override
    public void execute(Runnable runnable) {

    }
}
