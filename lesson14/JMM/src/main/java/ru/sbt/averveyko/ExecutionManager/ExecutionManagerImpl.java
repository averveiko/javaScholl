package ru.sbt.averveyko.ExecutionManager;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ThreadPool threadPool;
    private final Context context;

    public ExecutionManagerImpl(ThreadPool threadPool) {
        this.threadPool = threadPool;
        this.context = new ContextImpl(threadPool);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        for (Runnable task : tasks) {
            threadPool.execute(task);
        }

        threadPool.start();

        callback.run();

        return context;
    }
}
