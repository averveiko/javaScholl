package ru.sbt.averveyko.cacheproxy;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {

    @Override
    public double doHardWork(String work, int value) {
        //Тут происходит очень сложная работа
        return work.length() / (value * 3.14);
    }

    @Override
    public List<Double> run(String item) {
        List<Double> list = new ArrayList<>();
        list.add(item.length() * 1.5);
        list.add(item.length() * 2.5);
        list.add(item.length() * 3.5);
        return list;
    }
}
