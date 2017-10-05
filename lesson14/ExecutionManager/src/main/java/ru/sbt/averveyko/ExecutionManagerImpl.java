package ru.sbt.averveyko;

public class ExecutionManagerImpl implements ExecutionManager {
    private static int THREAD_COUNT = 5;
    private final FixedThreadPool threadPool = new FixedThreadPool(THREAD_COUNT);
    private final Context context;

    public ExecutionManagerImpl()  {
        this.context = new ContextImpl(threadPool);
    }
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        for (Runnable task : tasks) {
            threadPool.execute(task);
        }

        threadPool.start(callback);

        return context;
    }
}
