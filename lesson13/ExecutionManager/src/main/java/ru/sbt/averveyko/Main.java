package ru.sbt.averveyko;

public class Main {
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

        Runnable callback = () -> System.err.println("\nCallback run now!\n");

        ExecutionManagerImpl executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callback, tasks);

        System.err.println("context.isFinished(): " + context.isFinished());
        System.err.println("context.getCompletedTaskCount(): " + context.getCompletedTaskCount());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("context.isFinished(): " + context.isFinished());
        System.err.println("context.getCompletedTaskCount(): " + context.getCompletedTaskCount());
    }
}
