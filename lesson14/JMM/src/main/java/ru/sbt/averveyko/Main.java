package ru.sbt.averveyko;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Task<String> task = new Task<>(
                () -> "My callable task run in thread " + Thread.currentThread().getName()
        );

        Runnable runnable = () -> {
            try {
                System.out.println(task.get());
            } catch (ComputeException e) {
                System.out.println("ComputeException: " + e.getMessage());
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(runnable));
        }

        System.out.println(threads.size());

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
