package ru.sbt.averveyko.cacheproxy;

import java.util.List;

public interface IService {
    @Cache(cacheType = CacheType.FILE, zip = true, fileNamePrefix = "myPrefix", identityBy = {String.class, int.class})
    double doHardWork(String work, int value);

    @Cache(cacheType = CacheType.MEMORY, listLength = 2)
    List<Double> run(String item);
}