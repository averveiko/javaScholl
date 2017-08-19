package com.sbt.javaschool.averveyko;
/*Задан массив из n чисел (a1, a2,..., an).
  С ним два раза повторяют следующую процедуру: одновременно все максимумы в массиве делят на два.
  Нечетные числа при делении на два следует округлять вниз.
  Ваша задача — вывести массив после этих действий.

  Входные данные
  В первой строке входного файла дано число n (1 ≤ n ≤ 100) — количество чисел в массиве.
  Во второй строке через пробел записаны числа a1, a2,..., an (1 ≤ ai ≤ 100).

  Выходные данные
  Выведите массив после всех действий. Числа разделяйте пробелами.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        arr = process(arr);
        arr = process(arr);

        for (int el : arr) {
            System.out.print(el + " ");
        }
    }

    /**
     * <p>Находит максимальный элемент в массиве</p>
     *
     * @param arr массив
     * @return максимальный элемент
     */
    private static int arrMax(int[] arr) {
        int max = arr[0];
        for (int el: arr) {
            if (el > max) {
                max = el;
            }
        }
        return max;
    }

    /**
     * <p>обрабатывает массив в соотвествии с условием задачи</p>
     *
     * @param arr исходный массив
     * @return обработанный массив
     */
    private static int[] process (int[] arr) {
        int max = arrMax(arr);
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == max) {
                if (arr[i] % 2 != 0) arr[i] -= 1; //Если нечетное - отнимаем еденицу
                arr[i] = max / 2;
            }
        }
        return arr;
    }
}
