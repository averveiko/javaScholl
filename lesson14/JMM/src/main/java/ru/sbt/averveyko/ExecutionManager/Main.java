package ru.sbt.averveyko.ExecutionManager;

public class Main {
    private static final int THREAD_COUNT = 5;

    public static void main(String[] args) {
        Runnable[] tasks = new Runnable[] {
                new Task(0),
                new Task(1),
                new Task(2),
                new Task(3),
                new Task(4),
                new Task(5),
                new Task(6),
                new Task(7),
                new Task(8),
                new Task(9)
        };

        Runnable callback = () -> System.err.println("Callback run!");

        ThreadPool threadPool = new FixedThreadPool(THREAD_COUNT);

        ExecutionManagerImpl executionManager = new ExecutionManagerImpl(threadPool);
        Context context = executionManager.execute(callback, tasks);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(context.isFinished());
        System.err.println(context.getCompletedTaskCount());

    }
}
