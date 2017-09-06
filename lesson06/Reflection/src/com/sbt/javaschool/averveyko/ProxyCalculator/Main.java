package com.sbt.javaschool.averveyko.ProxyCalculator;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        ICalculator calc = (ICalculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{ICalculator.class},
                new ProxyHandler(new Calculator(), new CacheFileStorage("cache.csv"))
        );

        System.out.println("Результат : " + calc.eval("2 + 2"));
        System.out.println("Результат : " + calc.eval("2.5 + 3.5"));
        System.out.println("Результат : " + calc.eval("3.14 + 3.14"));
        System.out.println("Результат : " + calc.eval("3.14 + 3.14"));
        System.out.println("Результат : " + calc.eval("2.71828 + 2.71828"));
        System.out.println("Результат : " + calc.eval("2 + 2"));
        System.out.println("Результат : " + calc.eval("2.71828 + 2.71828"));
    }
}
