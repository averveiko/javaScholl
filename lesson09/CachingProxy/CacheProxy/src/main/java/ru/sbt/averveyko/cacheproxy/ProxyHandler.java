package ru.sbt.averveyko.cacheproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class ProxyHandler implements InvocationHandler {

    private final Object delegate;
    private final String cachePath;
    private final CacheType defaultCacheType;

    //Кэш:
    //Ключ - имя кэшируемого метода
    //Значение - map, где key - списки аргументов, значения - результат работы кэшируемой функции.
    private Map<String, Map<ArrayList, Object>> cache = new HashMap<>();

    public ProxyHandler(Object delegate, String cachePath, CacheType defaultCacheType) {
        this.delegate = delegate;
        this.cachePath = cachePath;
        this.defaultCacheType = defaultCacheType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Тут наша логика
        System.out.println("Вызов функции " + method.getName() + ", аргументы " + Arrays.toString(args));

        //Если требуется кеширование
        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);
            System.out.println("@Cache(cacheType=" + an.cacheType() +
                    ", fileNamePrefix=" + an.fileNamePrefix() +
                    ", zip=" + an.zip() +
                    ", identityBy=" + Arrays.toString(an.identityBy()) + ")");

            List<Object> identityArr = Arrays.asList(an.identityBy());

            ArrayList key = new ArrayList();
            for (Object arg : args) {
                if (identityArr.isEmpty())
                    key.add(arg); //Если массив identityBy не задан - добавляем все аргументы
                else if (identityArr.contains(ReflectionsUtils.GetPrimitiveClass(arg.getClass()))) {
                    System.out.println("\tИдентифицируем кэш по параметру: " + arg.getClass());
                    key.add(arg);  //Иначе только те, что заданы в аннотации
                }
            }


            if (an.cacheType() == CacheType.FILE) {
                //Если нет кеша для этого метода, пробуем смотреть на диске
                if (!this.cache.containsKey(method.getName())) {
                    String fileName = this.cachePath + method.getName() + ".cache";
                    Object methodCache = SerializableUtils.deserialize(fileName);
                    if (methodCache != null) {
                        System.out.println("Кеш для метода " + method.getName() + " был загружен из кэша " + fileName);
                        this.cache.put(method.getName(), (Map<ArrayList, Object>) methodCache);
                    }
                }
            }

            //Проверяем есть ли кэш для нашего метода
            if (this.cache.containsKey(method.getName())) {
                //Находим кэш конкретного метода
                Map<ArrayList, Object> methodCache = this.cache.get(method.getName());

                if (methodCache.containsKey(key)) {
                    //Возвращаем кэшированный результат
                    System.out.println("Для метода " + method.getName() + " ключ " + key + " найден в кэше, достаем результат из кэша");

                    return methodCache.get(key);
                } else {
                    //Рассчитываем результат и помещаем в кеш
                    Object value = method.invoke(delegate, args);
                    System.out.println("Помещено в кэш |" + method.getName() + "| " + key + " : " + value);
                    methodCache.put(key, value);

                    if (an.cacheType() == CacheType.FILE) {
                        String fileName = this.cachePath + method.getName() + ".cache";
                        System.out.println("Кэш записан в файл " + fileName);
                        SerializableUtils.serialize(this.cache.get(method.getName()), fileName);
                    }

                    return value;
                }
            } else {
                //Создаем кеш для этого метода
                System.out.println("Создан новый кэш для метода " + method.getName());
                Map<ArrayList, Object> methodCache = new HashMap<>();
                Object value = method.invoke(delegate, args);
                System.out.println("Помещено в кэш |" + method.getName() + "| " + key + " : " + value);
                methodCache.put(key, value);
                //Помещаем все в главный кеш
                this.cache.put(method.getName(), methodCache);

                if (an.cacheType() == CacheType.FILE) {
                    String fileName = this.cachePath + method.getName() + ".cache";
                    System.out.println("Кэш записан в файл " + fileName);
                    SerializableUtils.serialize(this.cache.get(method.getName()), fileName);
                }

                return value;
            }
        }
        //Метод не кешируется, просто вычисляем и возвращаем результат
        return method.invoke(delegate, args);
    }
}
