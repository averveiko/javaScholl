package com.sbt.javaschool.averveyko;

/*Дан текст, состоящий только из маленьких латинских букв и запятых.
  Последовательность подряд идущих букв, окруженную запятыми (началом, концом строки), назовем словом.
  Слово может быть пустым.

  Ваша задача — удалить из текста все слова короче k символов.

  Входные данные
  Первая строка входных данных — это текст, состоящий только из строчных латинских букв и запятых.
  Его длина не превосходит 1000 символов.
  На второй строке находится единственное число k — минимальная допустимая длина слова (0 ≤ k ≤ 1000).

  Выходные данные
  Выведите текст в таком же формате, как он задан — слова следует разделять запятыми.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        int k = in.nextInt();

        String[] words = text.split(",");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() >= k ) {
                if (sb.length() != 0) sb.append(","); //Если уже есть слова в билдере, добавляем ","
                sb.append(words[i]);
            }
        }
        System.out.println(sb.toString());
    }
}
