package ru.sbt.averveyko.cacheproxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProxyHandler implements InvocationHandler {

    private final Object delegate;
    private final String cachePath;
    private final CacheType defaultCacheType;

    //Кэш: ключи - списки аргументов, значения - резльтат работы кэшируемой функции.
    private Map<ArrayList, Object> cache = new HashMap<>();

    public ProxyHandler(Object delegate, String cachePath, CacheType defaultCacheType) {
        this.delegate = delegate;
        this.cachePath = cachePath;
        this.defaultCacheType = defaultCacheType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Тут наша логика
        System.out.println("Вызов функции " + method.getName());

        //Если требуется кеширование
        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);
            //DEBUG
            System.out.println("@Cache(cacheType=" + an.cacheType() + ", fileNamePrefix=" + an.fileNamePrefix() + ", zip=" + an.zip() + ")");

            //В качестве ключа используем список параметров функции
            ArrayList key = new ArrayList();
            for (Object arg : args) {
                key.add(arg);
            }

            //Проверяем есть ли в кэше результат
            for (ArrayList list : cache.keySet()) {
                if (list.equals(key)) {
                    //Нашлось - достаем из кэша
                    System.out.println("Ключ " + key + " найден в кэше, достаем результат из кэша");
                    return cache.get(key);
                }
            }

            //В качестве значения - результат вычисления функции
            Object value = method.invoke(delegate, args);

            //Помещаем в кэш
            System.out.println("Помещено в кэш " + key  + " : " + value);
            this.cache.put(key,value);

            if (an.cacheType() == CacheType.FILE) {
                //Сохраняем на диск
                String cacheFileName = this.cachePath  + method.getName() + ".cache";

                System.out.println("Кэш сохранен на диске " + cacheFileName);
                SerializableUtils.serialize(this.cache, cacheFileName);
            }
        }

        return method.invoke(delegate, args);
    }



}
