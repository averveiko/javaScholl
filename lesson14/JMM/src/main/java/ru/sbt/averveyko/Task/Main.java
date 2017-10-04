package ru.sbt.averveyko.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Task<String> task = new Task<>(
                () -> "My callable test " + "task"
        );

        Runnable runnable = () -> {
            try {
                task.get();
            } catch (ComputeException e) {
                System.out.println("ComputeException: " + e.getMessage());
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(runnable));
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
