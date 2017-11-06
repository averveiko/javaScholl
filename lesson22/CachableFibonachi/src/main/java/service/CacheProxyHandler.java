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

        if (method.isAnnotationPresent(Cachable.class)) {

            Cachable an = method.getAnnotation(Cachable.class);
            boolean persistent = an.persistent();

            int cachedResult = cache.get((int)args[0]);
            if (cachedResult != 0) {
                System.out.print("get value from cache ");
                return cachedResult;
            }

            System.out.print("calculate value ");
            int result = (int)method.invoke(delegate, args);

            cache.put((int)args[0], result, persistent);
            return result;
        }

        return method.invoke(delegate, args);
    }
}
