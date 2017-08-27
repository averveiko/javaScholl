package com.sbt.javaschool.averveyko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        //Собираем весь ввод
        StringBuffer sb = new StringBuffer();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while( (line = in.readLine()) != null) {
            sb.append(line);
        }

        //Переводим введенный текст в нижний регистр
        String text = sb.toString().toLowerCase();
        //Сплит по всем пробельным символам
        List<String> words = new ArrayList<>();
        for (String word : text.split("\\s+")) {
            //Не даем шанса пустым строкам
            if (word.length() > 0) words.add(word);
        }
        //Получаем список уникальных слов
        Set<String> uniqueWords = new HashSet<>(words);

        //Тут будем хранить частоту вхождений слов в тексте
        Map<String, Integer> wordFrequency = new TreeMap<>();

        for (String word : uniqueWords) {
            wordFrequency.put(word,Collections.frequency(words,word));
        }

        Integer max = Collections.max(wordFrequency.values());

        for (String key : wordFrequency.keySet()) {
            Integer count = wordFrequency.get(key);
            if (count.equals(max)) System.out.println(key);
        }
    }
}
