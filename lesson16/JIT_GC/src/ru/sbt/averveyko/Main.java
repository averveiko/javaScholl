package ru.sbt.averveyko;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 1_000_000; i++) {
            map.put(i,"value" + i);
        }

        Thread.sleep(10000);
    }
}
