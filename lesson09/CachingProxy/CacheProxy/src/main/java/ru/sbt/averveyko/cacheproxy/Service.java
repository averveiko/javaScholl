package ru.sbt.averveyko.cacheproxy;

public class Service implements IService {

    @Override
    public double doHardWork(String work, int value) {
        //Тут происходит очень сложная работа
        return work.length() / (value * 3.14);
    }

    @Override
    public double run(String item) {
        return item.length() * 1.5;
    }
}
