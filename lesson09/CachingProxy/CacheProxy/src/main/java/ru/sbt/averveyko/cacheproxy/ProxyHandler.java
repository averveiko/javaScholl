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

        Object returnValue;

        System.out.println("Вызов функции " + method.getName() + ", аргументы " + Arrays.toString(args));

        //Если требуется кеширование
        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);
            System.out.println("@Cache(cacheType=" + an.cacheType() +
                    ", fileNamePrefix=" + an.fileNamePrefix() +
                    ", zip=" + an.zip() +
                    ", identityBy=" + Arrays.toString(an.identityBy()) + ")" +
                    ", listLength=" + an.listLength() + ")");

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
                    String prefix = (an.fileNamePrefix().length() > 0) ? an.fileNamePrefix() : method.getName();
                    String fileName = this.cachePath + prefix + ".cache";
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
                    returnValue = method.invoke(delegate, args);

                    if (an.listLength() != -1 && List.class.isAssignableFrom(returnValue.getClass())) {
                        //Обрезаем лист до нужной длины
                        returnValue = ((List) returnValue).subList(0, an.listLength());
                    }

                    System.out.println("Помещено в кэш |" + method.getName() + "| " + key + " : " + returnValue);
                    methodCache.put(key, returnValue);


                    if (an.cacheType() == CacheType.FILE) {
                        String prefix = (an.fileNamePrefix().length() > 0) ? an.fileNamePrefix() : method.getName();
                        String fileName = this.cachePath + prefix + ".cache";
                        System.out.println("Кэш записан в файл " + fileName);
                        SerializableUtils.serialize(this.cache.get(method.getName()), fileName);
                    }

                    return returnValue;
                }
            } else {
                //Создаем кеш для этого метода
                System.out.println("Создан новый кэш для метода " + method.getName());
                Map<ArrayList, Object> methodCache = new HashMap<>();

                returnValue = method.invoke(delegate, args);

                if (an.listLength() != -1 && List.class.isAssignableFrom(returnValue.getClass())) {
                    //Обрезаем лист до нужной длины
                    returnValue = ((List) returnValue).subList(0, an.listLength());
                }
                System.out.println("Помещено в кэш |" + method.getName() + "| " + key + " : " + returnValue);
                methodCache.put(key, returnValue);

                //Помещаем все в главный кеш
                this.cache.put(method.getName(), methodCache);

                if (an.cacheType() == CacheType.FILE) {
                    String prefix = (an.fileNamePrefix().length() > 0) ? an.fileNamePrefix() : method.getName();
                    String fileName = this.cachePath + prefix + ".cache";
                    System.out.println("Кэш записан в файл " + fileName);
                    SerializableUtils.serialize(this.cache.get(method.getName()), fileName);
                }

                return returnValue;
            }
        }
        //Метод не кешируется, просто вычисляем и возвращаем результат
        return method.invoke(delegate, args);
    }
}
