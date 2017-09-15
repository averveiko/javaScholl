package ru.sbt.averveyko.main;

import ru.sbt.averveyko.cacheproxy.CacheProxy;
import ru.sbt.averveyko.cacheproxy.CacheType;
import ru.sbt.averveyko.cacheproxy.IService;
import ru.sbt.averveyko.cacheproxy.Service;

public class Main {
    //Каталог хранения кэша вычислений
    private static final String CACHE_PATH = "cache";
    private static final CacheType DEFAULT_CACHE_TYPE = CacheType.FILE;

    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(CACHE_PATH, DEFAULT_CACHE_TYPE);

        IService service = cacheProxy.cache(new Service());
        System.out.println(service.doHardWork("work1",4));

    }

}
