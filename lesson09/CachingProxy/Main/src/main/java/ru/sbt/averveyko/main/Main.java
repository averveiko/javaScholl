package ru.sbt.averveyko.main;

import ru.sbt.averveyko.cacheproxy.CacheProxy;
import ru.sbt.averveyko.cacheproxy.CacheType;
import ru.sbt.averveyko.cacheproxy.IService;
import ru.sbt.averveyko.cacheproxy.Service;

import java.io.File;

public class Main {
    //Каталог хранения кэша вычислений
    private static final String CACHE_PATH = "cache" + File.separator;
    private static final CacheType DEFAULT_CACHE_TYPE = CacheType.FILE;

    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(CACHE_PATH, DEFAULT_CACHE_TYPE);

        IService service = cacheProxy.cache(new Service());
        System.out.println(service.doHardWork("work1",4));
        System.out.println(service.doHardWork("work1",6));
        System.out.println(service.doHardWork("work1",4));

//        ArrayList list1 = new ArrayList();
//        list1.add("Test");
//        list1.add(14);
//
//        ArrayList list2 = new ArrayList();
//        list2.add("Test");
//        list2.add(14);
//
//        System.out.println(list1.equals(list2));

//        System.out.println(list1.get(0).getClass());
//        System.out.println(list1.get(1).getClass());


    }

}
