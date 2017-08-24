package com.sbt.javashool.averveyko;
/*Заданы n целых чисел. Выведите индекс (позицию) первого из минимальных элементов последовательности.
Элементы последовательности занумерованы от 1 слева направо.

Входные данные
В первой строке записано целое число n (1 ≤ n ≤ 10000).
Вторая строка содержит последовательность целых чисел a1, a2,..., an (-10000 ≤ ai ≤ 10000).
Выходные данные
Выведите искомый индекс.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if (n < 1) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[n];
        int min = 0, minindex = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (i == 0) {
                min = arr[i];
            } else {
                if (arr[i] < min) {
                    min = arr[i];
                    minindex = i;
                }
            }
        }

        System.out.println(minindex + 1);

    }
}
