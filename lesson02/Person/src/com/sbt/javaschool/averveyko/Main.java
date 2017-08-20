package com.sbt.javaschool.averveyko;

public class Main {

    public static void main (String[] args) {

        Person Vasya = new Person(true, "Vasya");
        Person Masha = new Person(false, "Masha");
        Person Petya = new Person(true, "Petya");
        Person Elena = new Person(false, "Elena");

        System.out.println("Try Vasya marry Masha ..");
        boolean isMarried = Vasya.marry(Masha);
        System.out.println("Vasya.marry(Masha)" + isMarried);
        System.out.println();

        System.out.println("Try Vasya marry Petya ..");
        isMarried = Vasya.marry(Petya);
        System.out.println("Vasya.marry(Petya)" + isMarried);
        System.out.println();

        System.out.println("Try Petya marry Elena ..");
        isMarried = Petya.marry(Elena);
        System.out.println("Petya.marry(Elena)" + isMarried);
        System.out.println();

        System.out.println("Try Vasya marry Elena ..");
        isMarried = Vasya.marry(Elena);
        System.out.println("Vasya.marry(Elena)" + isMarried);
        System.out.println();

        System.out.println("Try Masha marry Vasya ..");
        isMarried = Masha.marry(Vasya);
        System.out.println("Masha.marry(Vasya)" + isMarried);
        System.out.println();
    }
}
