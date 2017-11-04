package service;

import model.Cache;

import java.lang.reflect.Proxy;

public class CacheProxy {
    public static ICalculator cache(Calculator calculator) {
        return (ICalculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{ICalculator.class},
                new CacheProxyHandler(calculator, new Cache())
        );
    }
}
