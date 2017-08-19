package com.sbt.javaschool.averveyko;
/*Определим слово как последовательность латинских букв алфавита.
Все остальные символы являются разделителями.

Дан текст, состоящий из символов с номерами от 32 до 127 (см. таблицу символов ASCII).
Ваша задача — найти количество букв в наидлинейшем слове из этого текста.

Входные данные
Входные данные состоят из единственной строки, длиной не более 10000 символов — исходный текст.
Выходные данные
Выведите единственное число — количество букв в наидлинейшем слове в данном тексте.*/
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        int maxLen = 0, len = 0;
        boolean previousIsLetter = false;

        for (int i = 0; i < text.length(); i++){
            if ((text.charAt(i) >= 65 &&  text.charAt(i) <= 90) ||
                    (text.charAt(i) >= 97 &&  text.charAt(i) <= 122)) {
                if (!previousIsLetter) previousIsLetter = true;
                ++len;
            } else {
                previousIsLetter = false;
                if(len > maxLen) maxLen = len;
                len = 0;
            }
        }
        if (len > maxLen) maxLen = len;
        System.out.println(maxLen);
    }
}
