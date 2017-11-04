package service;

import model.ICache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CacheProxyHandler implements InvocationHandler {
    private final Object delegate;
    private final ICache cache;

    public CacheProxyHandler(Object delegate, ICache cache) {
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //System.out.println("start invoke");

        if (method.isAnnotationPresent(Cachable.class)) {
            //System.out.println("Cachable present");

            int cachedResult = cache.get((int)args[0]);
            if (cachedResult != 0) {
                //System.out.println("return cached value");
                return cachedResult;
            }

            //System.out.println("calculate value and put to cache");
            int result = (int)method.invoke(delegate, args);

            cache.put((int)args[0], result);
            return result;
        }

        //System.out.println("end invoke");
        return method.invoke(delegate, args);
    }
}
