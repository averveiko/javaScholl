package com.sbt.javaschool.averveyko.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */

    public static void assign(Object to, Object from) {

        Class clazzFrom = from.getClass();
        Class clazzTo = to.getClass();

        Method[] methods = clazzFrom.getMethods(); //получаем все public методы
        for (Method method : methods) {

            if (method.getDeclaringClass() == Object.class) //Методы класса Object пропускаем
                continue;

            if (method.getName().startsWith("get")) { //Это getter
                System.out.println("\nНайден getter " + method);

                //Генерируем имя сеттера-получателя
                String setterName = "set" + method.getName().substring(3);
                System.out.println("Сеттер-получатель: " + setterName);
                //Тип принимаемого параметра
                Type parameterType = method.getAnnotatedReturnType().getType();

                try {
                    Method setter = clazzTo.getMethod(setterName, (Class) parameterType);
                    System.out.println("Найден сеттер: " + setter);

                    try {
                        setter.invoke(to, method.invoke(from));
                        System.out.println("Значение установлено.");
                    } catch (IllegalAccessException e) {
                        System.out.println("Нет доступа к методу " + method);
                    } catch (InvocationTargetException e) {
                        System.out.println("Возникли пробелемы с вызовом метода " + method);
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println("В получателе нет метода " + setterName + " с параметром " + parameterType);
                }
            }
        }
    }
}
