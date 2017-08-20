package com.sbt.javaschool.averveyko;
/*Заданы два массива целых положительных чисел: a и b.
Ваша задача сформировать такой массив c, содержащий все элементы массива a, которые встречаются в b.
В массиве c элементы должны идти в том же порядке, что и a, просто пропускайте те элементы, что не присутствуют в b.

Входные данные
В первой строке записано целое положительное число n (1 ≤ n ≤ 10000), n — количество элементов массива a.
Вторая строка содержит n целых чисел: a1, a2,..., an (1 ≤ ai ≤ 10000). Числа записаны через пробел.
Третья и четвертая строки содержат описание массива b в формате, полностью совпадающем с форматом описания массива a.

Выходные данные
Выведите искомый массив c в формате, повторяющем формат массивов во входных данных.
Если искомый массив пустой, то в первую строку выведите 0, а вторую оставьте пустой.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n1 = in.nextInt();
        int[] a = new int[n1];
        for (int i=0 ;i<n1; i++) {
            a[i] = in.nextInt();
        }

        int n2 = in.nextInt();
        int[] b = new int[n2];
        for (int i=0 ;i<n2; i++) {
            b[i] = in.nextInt();
        }

        int[] c = new int[n1];
        int ci =0;
        for (int el : a) {
            if (inArray(b, el)) {
                c[ci++] = el;
            }
        }

        System.out.println(ci);
        for (int i=0; i<ci; i++) {
            System.out.print(c[i] + " ");
        }
    }

    private static boolean inArray (int[] arr, int value) {
        for (int i : arr) {
            if (i == value) return true;
        }
        return false;
    }
}
