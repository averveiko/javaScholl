package service;

import model.Cache;
import model.ICache;

import java.lang.reflect.Proxy;

public class CacheProxy {
    public static ICalculator cache(Calculator calculator, ICache cache) {
        return (ICalculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{ICalculator.class},
                new CacheProxyHandler(calculator, cache)
        );
    }
}
