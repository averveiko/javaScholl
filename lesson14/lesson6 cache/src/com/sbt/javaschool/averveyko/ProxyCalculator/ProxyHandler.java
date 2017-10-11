package com.sbt.javaschool.averveyko.ProxyCalculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class ProxyHandler implements InvocationHandler {

    private final Object delegate;
    private final CacheFileStorage fs;
    private final Map<String, Double> cache;

    public ProxyHandler(Object delegate, CacheFileStorage fs) {
        this.delegate = delegate;
        this.fs = fs;
        this.cache = fs.load();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Если метод не eval - сразу вычисляем и отдаем результат
        if (!method.getName().equals("eval")) return method.invoke(delegate, args);

        //Проверяем, если в кэше уже вычисленное значение
        if (cache.containsKey((String) args[0])) {
            System.out.println("Результат выражения " + (String) args[0] + " получен из кэша");
            return cache.get((String) args[0]);
        }

        //Иначе вычисляем его
        Object result = method.invoke(delegate, args);
        //И заносим в кэш
        cache.put((String) args[0], (Double) result);
        System.out.println("Результат выражения " + (String) args[0] + " сохранен в кэше");

        //Сохраняем кэш на диск
        fs.save(cache);

        return result;
    }
}
