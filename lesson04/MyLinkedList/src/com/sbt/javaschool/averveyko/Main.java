package com.sbt.javaschool.averveyko;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    int size = 0;

    public static void main(String[] args) {

        MyLinkedList<Number> myList = new MyLinkedList<>();

        myList.add(1);
        myList.add(2L);
        //myList.add(null); тоже работает
        myList.add(3.0);
        myList.add(new BigInteger("4"));

        System.out.println("\nСодержание нашей коллекции:");
        for (Number number : myList) {
            System.out.print("[" + number + "] ");
        }

        System.out.println("\nВставляем элементы в середину и конец:");
        myList.add(2, 3.0);
        myList.add(4, new BigInteger("4"));
        for (Number number : myList) {
            System.out.print("[" + number + "] ");
        }

        System.out.println("\nУдаляем элементы с индексом 2 и 5:");
        myList.remove(2);
        myList.remove(4);
        for (Number number : myList) {
            System.out.print("[" + number + "] ");
        }

        System.out.println("\nДобавляем элементы из другой коллекции");
        List<Integer> src = new  ArrayList<>();
        src.add(5);
        src.add(6);
        src.add(7);
        myList.addAll(src);
        for (Number number : myList) {
            System.out.print("[" + number + "] ");
        }

        System.out.println("\nКопируем элементы в другую коллекцию (содержимое другой коллекции):");
        List<Number> dst = new LinkedList<>();
        myList.copy(dst);
        for (Number number : dst) {
            System.out.print("[" + number + "] ");
        }

        System.out.println();
    }
}
