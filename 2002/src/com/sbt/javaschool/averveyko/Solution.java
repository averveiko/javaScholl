package com.sbt.javaschool.averveyko;
/*Задано n целых чисел. Выведите их сумму.
  Входные данные
  В первой строке задано целое число n (1 ≤ n ≤ 10000). Вторая строка содержит n целых чисел, каждое от 1 до 10000, включительно.
  Выходные данные
  Выведите искомую сумму.*/

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
	    int n = in.nextInt();

	    int[] arr = new int[n];

	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = in.nextInt();
        }

        int sum = 0;

        for (int el: arr) {
            sum += el;
        }

        System.out.println(sum);
    }
}
