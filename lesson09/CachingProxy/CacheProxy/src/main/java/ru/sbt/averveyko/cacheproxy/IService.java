package ru.sbt.averveyko.cacheproxy;

public interface IService {
    @Cache(cacheType = CacheType.MEMORY)
    double doHardWork(String work, int value);
}
