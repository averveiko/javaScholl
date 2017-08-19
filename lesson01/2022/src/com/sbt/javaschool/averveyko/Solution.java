package com.sbt.javaschool.averveyko;
/*Задан массив n целых чисел (a1, a2,..., an).
  Ваша задача — найти количество пар индексов (i, j), что i ≠ j и ai делит aj.
  Обратите внимание, что пары (i, j) и (j, i) считаются различными.

  Входные данные
  В первой строке входного файла дано число n (1 ≤ n ≤ 100) — количество чисел в массиве.
  Во второй строке через пробел записаны числа a1, a2,..., an (1 ≤ ai ≤ 100).

  Выходные данные
  Выведите единственное число — количество пар, удовлетворяющий условию.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (arr[i] % arr[j] == 0) ++count;
                }
            }
        }
        System.out.println(count);
    }
}
