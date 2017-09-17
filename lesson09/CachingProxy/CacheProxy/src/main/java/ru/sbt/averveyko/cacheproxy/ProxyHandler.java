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
        System.out.println("\nВызов функции " + method.getName() + ", аргументы " + Arrays.toString(args));

        //Если требуется кэширование
        if (method.isAnnotationPresent(Cache.class)) {
            Cache an = method.getAnnotation(Cache.class);
            System.out.println("Аннотации метода: @Cache(cacheType=" + an.cacheType() +
                    ", fileNamePrefix=" + an.fileNamePrefix() +
                    ", zip=" + an.zip() +
                    ", identityBy=" + Arrays.toString(an.identityBy()) + ")" +
                    ", listLength=" + an.listLength() + ")");

            //Формируем имя файла кэша для записи\чтения
            String prefix = (an.fileNamePrefix().length() > 0) ? an.fileNamePrefix() : method.getName();
            String fileName = this.cachePath + prefix + ".cache";

            Map<ArrayList, Object> methodCache = new HashMap<>();

            if (an.cacheType() == CacheType.FILE) {
                //Если нет кэша для этого метода, попробуем загрузить с диска
                if (!this.cache.containsKey(method.getName())) {
                    Object loadedCache = SerializableUtils.deserialize(fileName);
                    if (loadedCache != null) {
                        methodCache = (Map<ArrayList, Object>) loadedCache;
                        System.out.println("кэш для метода " + method.getName() + " был загружен из файла " + fileName);
                        this.cache.put(method.getName(), (Map<ArrayList, Object>) methodCache);
                    }
                }
            }

            //Список идентификационных классов аргументов из аннотации
            List<Object> identityArr = Arrays.asList(an.identityBy());

            //Выбираем идентифкационные аргументы
            ArrayList keys = new ArrayList();
            for (Object arg : args) {
                if (identityArr.isEmpty())
                    keys.add(arg); //Если массив identityBy не задан - добавляем все аргументы
                else if (identityArr.contains(ReflectionsUtils.GetPrimitiveClass(arg.getClass()))) {
                    System.out.println("\tИдентифицируем кэш по параметру: " + arg.getClass());
                    keys.add(arg);  //Иначе только те, что заданы в аннотации
                }
            }

            //Если в кэше есть наш метод
            if (this.cache.containsKey(method.getName())) {
                //Находим кэш конкретного метода
                methodCache = this.cache.get(method.getName());
                //Если есть кэш с нужными аргументами
                if (methodCache.containsKey(keys)) {
                    //Возвращаем кэшированный результат
                    System.out.println("Для метода " + method.getName() + " ключ " + keys + " найден в кэше, достаем результат из кэша");
                    return methodCache.get(keys);
                }
            }

            //Рассчитываем результат и помещаем в кэш
            Object returnValue = method.invoke(delegate, args);

            if (an.listLength() != -1 && List.class.isAssignableFrom(returnValue.getClass())) {
                //Обрезаем лист до нужной длины, при необходимости
                returnValue = ((List) returnValue).subList(0, an.listLength());
            }

            System.out.println("Помещено в кэш |" + method.getName() + "| " + keys + " : " + returnValue);
            methodCache.put(keys, returnValue);
            this.cache.put(method.getName(), methodCache);

            if (an.cacheType() == CacheType.FILE) {
                System.out.println("Кэш записан в файл " + fileName);
                SerializableUtils.serialize(this.cache.get(method.getName()), fileName);
            }

            return returnValue;
        }
        //Метод не кэшируется, просто вычисляем и возвращаем результат
        return method.invoke(delegate, args);
    }
}
