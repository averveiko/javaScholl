package ru.sbt.averveyko.main;

import ru.sbt.averveyko.cacheproxy.CacheProxy;
import ru.sbt.averveyko.cacheproxy.CacheType;
import ru.sbt.averveyko.cacheproxy.IService;
import ru.sbt.averveyko.cacheproxy.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    //Каталог хранения кэша вычислений
    private static final String CACHE_PATH = "cache" + File.separator;
    private static final CacheType DEFAULT_CACHE_TYPE = CacheType.FILE;

    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(CACHE_PATH, DEFAULT_CACHE_TYPE);

        IService service = cacheProxy.cache(new Service());

        System.out.println("\n>Метод doHardWork");
        System.out.println("Результат: " + service.doHardWork("work2",422));
        System.out.println("Результат: " + service.doHardWork("work2",422));
        System.out.println("Результат: " + service.doHardWork("work1",4));
        System.out.println("Результат: " + service.doHardWork("work1",4));

        System.out.println("\n>Метод run");
        System.out.println("Результат: " + service.run("Test"));
        System.out.println("Результат: " + service.run("Test"));
        System.out.println("Результат: " + service.run("Test Test"));
        System.out.println("Результат: " + service.run("Test Test"));

    }
}
