package ru.sbt.averveyko.cacheproxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private final Object delegate;
    private final String cachePath;
    private final CacheType defaultCacheType;


    public ProxyHandler(Object delegate, String cachePath, CacheType defaultCacheType) {
        this.delegate = delegate;
        this.cachePath = cachePath;
        this.defaultCacheType = defaultCacheType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Тут наша логика
        System.out.println("run " + method.getName());

        //Если требуется кеширование
        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);
            //DEBUG
            System.out.println(an.cacheType() + " " + an.fileNamePrefix()+  " " + an.zip());

        }

        Object result = method.invoke(delegate, args);
        return result;
    }



}
