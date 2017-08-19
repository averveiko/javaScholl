package com.sbt.javaschool.averveyko;
/*Дан набор из n слов, состоящих из маленьких латинских букв.
  Будем называть слово странным, если в нем встречаются 3 или более гласные буквы подряд.
  Ваша задача — удалить из данного набора все странные слова.
  Гласными буквами в латинском алфавите считаются e,y,u,i,o,a.

  Входные данные
  В первой строке содержится число n — количество слов в наборе, n не превосходит 100.
  Далее в n строках по одному на строке содержатся слова из набора.
  Слова состоят только из маленьких латинских букв. Длина каждого слова не менее 1 и не более 20 символов.

  Выходные данные
  Выведите все слова из набора, не являющиеся странными.
  Каждое слово выводите на отдельной строке в том порядке, в котором они заданы во входных данных.*/

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    int n = in.nextInt();

	    String[] arr = new String[n];

	    for (int i = 0; i < n; i++) {
	        arr[i] = in.next();
        }

        //Вывод
        for (String s : arr) {
            if (!isStrange(s)) System.out.println(s);
        }
    }

    private static boolean isStrange (String word) {
        //Любой текст, минимум 3 повторения любой согласной, любой текст
        if (word.matches(".*[eyuioa]{3,}.*")) return true;
        return false;
    }
}