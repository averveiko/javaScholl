package ru.sbt.averveyko.threadpool;

/**
 * Задача для ThreadPool
 */
public class Work implements Runnable {
    private final int id;

    public Work(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.err.println("[START] Work id " + id + " in thread " + Thread.currentThread().getName());

        double a = 0;
        for (int i = 0; i < 2000000; i++) {
            a = a + Math.tan(a) + Math.sin(a) + Math.cos(a);
        }
        System.err.println("[FINISH] Work id " + id + " in thread " + Thread.currentThread().getName());
    }
}
