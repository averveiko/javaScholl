package ru.sbt.averveyko.cacheproxy;

public interface IService {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "myPrefix", identityBy = {String.class, int.class})
    double doHardWork(String work, int value);

    @Cache(cacheType = CacheType.MEMORY)
    double run(String item);
}
