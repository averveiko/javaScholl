package ru.sbt.averveyko.cacheproxy;

public interface IService {
    @Cache(cacheType = CacheType.FILE)
    double doHardWork(String work, int value);
}
