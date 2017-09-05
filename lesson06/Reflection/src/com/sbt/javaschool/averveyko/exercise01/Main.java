package com.sbt.javaschool.averveyko.exercise01;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Alex", 32);

        Class clazz = person.getClass();

        // Вывести на консоль все методы класса, включая родительские и приватные
        while (clazz != null) {
            System.out.println("\nMethods declared in " + clazz);

            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                System.out.println(declaredMethod);
            }

            clazz = clazz.getSuperclass(); //Переходим к родителю
        }

        //Вывести все геттеры класса
        clazz = person.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        System.out.println("\nGetters declared in " + clazz);
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith("get"))
                System.out.println(declaredMethod);
        }

        //Проверить что все строковые константы имеют значение = их имени
        System.out.println("\nString const == value");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                    System.out.print(field.getName() + " ");
                    try {
                        System.out.println(field.getName().equals(field.get(person)));
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}

class Person {
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";


    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String getPass() {
        return "secret password";
    }
}
