package com.sbt.javaschool.averveyko;

/*Заданы a и b. Выведите a+b.

  Входные данные
  В единственной строке входных данных заданы целочисленные a и b (1 ≤ a,b ≤ 1000).

  Выходные данные
  Выведите a+b.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();

        if ((a >= 1 && a <= 1000) && (b >= 1 && b <= 1000)) {
            int c = a + b;
            System.out.println(c);
        } else {
            System.out.println("(1 ≤ " + a + "," + b + " ≤ 1000)");
        }
    }
}