package ru.sbt.averveyko;

public class ContextImpl implements Context {
    private final FixedThreadPool threadPool;

    public ContextImpl(FixedThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public int getCompletedTaskCount() {
        return threadPool.getCompletedTaskCount();
    }

    @Override
    public int getFailedTaskCount() {
        return threadPool.getFailedTaskCount();
    }

    @Override
    public void interrupt() {
        threadPool.interrupt();
    }

    @Override
    public int getInterruptedTaskCount() {
        return threadPool.getInterruptedTaskCount();
    }

    @Override
    public boolean isFinished() {
        return threadPool.isFinished();
    }
}