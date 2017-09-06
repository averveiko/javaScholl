package com.sbt.javaschool.averveyko.Annotation;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        CheckAnnotation checkAnnotation = new CheckAnnotation();

        Person person = new Person("This is very long name of person");
        checkAnnotation.validateStringLength(person);
    }
}

class Person {
    @ValidLength(min = 4, max = 10)
    public final String name;

    public Person(String name) {
        this.name = name;
    }
}

class CheckAnnotation {
    public void validateStringLength(Object o) throws Exception {
        Class<?> clazz = o.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ValidLength.class)) {
                ValidLength an = field.getAnnotation(ValidLength.class);
                int max = an.max();
                int min = an.min();

                String value = field.get(o).toString();
                if (value.length() < min || value.length() > max) {
                    throw new IllegalArgumentException(field.getName() +
                    " length should be between " + min + " and " + max);
                }
            }
        }
    }
}
