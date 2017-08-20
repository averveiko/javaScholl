package com.sbt.javaschool.averveyko;
/*Альтернированной суммой заданной последовательности n чисел a1, a2,..., an называется число s=a1-a2+a3-a4+....
В альтернированной сумме знаки слагаемых чередуются, альтернация начинается со знака "+".
По заданной последовательности целых чисел выведите ее альтернированную сумму.

Входные данные
В первой строке задано целое число n (1 ≤ n ≤ 10000).
Вторая строка содержит n целых чисел, каждое от 1 до 10000 включительно.
Выходные данные
Выведите искомую альтернированную сумму.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);

	    int n = in.nextInt();
	    int[] arr = new int[n];

	    for (int i=0; i<n; i++){
	        arr[i] = in.nextInt();
        }

        boolean minus = false;

        int s = 0;

        for (int el : arr) {
            if (minus) {
                s -= el;
            } else {
                s += el;
            }
            minus = !minus;
        }

        System.out.println(s);
    }
}
