package ru.sbt.averveyko.cacheproxy;

import java.lang.reflect.Proxy;

public class CacheProxy {
    private final String cachePath;
    private final CacheType defaultCacheType;

    public CacheProxy(final String cachePath, final CacheType defaultCacheType) {
        this.cachePath = cachePath;
        this.defaultCacheType = defaultCacheType;
    }

    public IService cache(final Service service) {
        return (IService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{IService.class},
                new ProxyHandler(service, cachePath, defaultCacheType));
    }
}
