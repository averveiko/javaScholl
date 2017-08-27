package com.sbt.javaschool.averveyko;
/*Дан текст, ваша задача — найти слово, которое встречается в тексте наибольшее количество раз.
Текст состоит только из латинских букв, пробелов, переводов строк.

Слово — это последовательность подряд идущих латинских букв, регистр не имеет значения.
Если искомых слов несколько, ваша задача — найти все такие слова.

Входные данные
Входные данные состоят только из латинских букв, пробелов и символов перевода строки.
Гарантируется, что хотя бы одно слово в текст присутствует.
Выходные данные
Выведите все слова, которые встречаются наибольшее количество раз, при их следут приводить к нижнему регистру,
каждое слово выводите на отдельной строке. Слова выводите в лексикографическом порядке.
Размер входного файла не превосходит 100 Кб. */

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //Собираем весь ввод
        StringBuffer sb = new StringBuffer();
        while (in.hasNextLine()) {
            sb.append(in.nextLine());
        }
        in.close();
        //Переводим введенный текст в нижний регистр
        String text = sb.toString().toLowerCase();
        //Сплит по всем пробельным символам
        List<String> words = new ArrayList<>(Arrays.asList(text.split("\\s+")));
        //Получаем список уникальных слов
        Set<String> uniqueWords = new HashSet<>(words);
        //Тут будем хранить частоту вхождений слов в тексте
        List<WordWithFreq> wordFrequency = new ArrayList<>(uniqueWords.size());
        for (String word : uniqueWords) {
            wordFrequency.add(new WordWithFreq(word, Collections.frequency(words,word)));
        }
        //Сортируем по частоте
        Collections.sort(wordFrequency, new Comparator<WordWithFreq>() {
            @Override
            public int compare(WordWithFreq o1, WordWithFreq o2) {
                return (o1.freq < o2.freq ? 1 : (o1.freq == o2.freq ? 0 : -1));
            }
        });
        //В этот список поместим только слова с наибольшей частотой вхождений
        List<WordWithFreq> result = new ArrayList<>();
        int maxFreq = wordFrequency.get(0).freq;
        for (WordWithFreq word : wordFrequency) {
            if (word.freq == maxFreq)
                result.add(word);
            else
                break;
        }
        //Сортируем в лексикографическом порядке
        Collections.sort(result, new Comparator<WordWithFreq>() {
            @Override
            public int compare(WordWithFreq o1, WordWithFreq o2) {
                return o1.word.compareTo(o2.word);
            }
        });
        //Выводим результат
        for (WordWithFreq wordWithFreq : result) {
            System.out.println(wordWithFreq.word);
        }
    }
}

class WordWithFreq {
    public final String word;
    public final int freq;

    public WordWithFreq(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
}
