package ru.sbt.averveyko.threadpool;


public class Main {
    public static void main(String[] args) {
        FixedThreadPool  fixedThreadPool = new FixedThreadPool(3);
        for (int i = 0; i < 8; i++ )
            fixedThreadPool.execute(new Work(i));

        fixedThreadPool.start();

        fixedThreadPool.execute(new Work(8));
        fixedThreadPool.execute(new Work(9));
    }
}
