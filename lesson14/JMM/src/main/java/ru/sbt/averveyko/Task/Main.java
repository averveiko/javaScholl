package ru.sbt.averveyko.Task;

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

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
