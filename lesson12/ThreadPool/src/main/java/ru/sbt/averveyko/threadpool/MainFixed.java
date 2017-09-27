package ru.sbt.averveyko.threadpool;


public class MainFixed {
    public static void main(String[] args) {
        FixedThreadPool  fixedThreadPool = new FixedThreadPool(4);
        for (int i = 0; i < 8; i++ )
            fixedThreadPool.execute(new Work(i));

        fixedThreadPool.start();

        //Подождем немного и запустим еще 2 задачи
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fixedThreadPool.execute(new Work(8));
        fixedThreadPool.execute(new Work(9));
    }
}
