package com.sbt.javaschool.averveyko;

import java.util.LinkedList;
import java.util.List;

public class Main {

    int size = 0;

    public static void main(String[] args) {
	    // write your code here
        MyLinkedList<String> myList = new MyLinkedList();
        myList.add("One");
        myList.add("Two");
        myList.add("Three");
        myList.add("Four");
        myList.add("Five");
        myList.add("Remove me!");
        myList.add("Six");

        //myList.add(2, "Three");

        Object var0 = myList.node(0);
        Object var1 = myList.node(1);
        Object var2 = myList.node(2);
        Object var3 = myList.node(3);
        Object var4 = myList.node(4);

        System.out.println(myList.remove(5));

    }
}
