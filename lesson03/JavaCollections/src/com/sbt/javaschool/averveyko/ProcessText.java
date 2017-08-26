package com.sbt.javaschool.averveyko;

import java.util.*;

public class ProcessText {

    private String text;            //Весь текст файла
    private String[] wordArray;     //Массив всех слов в нижнем регистре
    
    public ProcessText(String text) {

        this.text = text;

        String[] words = text.split("\\s+"); //Сплит по всем пробельным символам

        this.wordArray = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            this.wordArray[i] = words[i].toLowerCase(); //Переводим все слова в нижний регистр
        }
    }

    //Всего слов в файле
    public int allWordsCount () {
        return wordArray.length;
    }

    //1) Подсчитайте кол-во различных слов в файле
    public int uniqueWordsCount () {
        
        Set<String> words = new HashSet<>(Arrays.asList(wordArray));

        return words.size();
    }
    //2) Выведите список различных слов файла, отсортированный по возрастанию их длины
    public void printSortedUniqueWords () {
        Set<String> uniqWords = new HashSet<>(Arrays.asList(wordArray));

        List<String> uniqWordsToSort = new ArrayList<>(uniqWords);
        //Компаратор по тексту (по умолчанию и так сравнивает строки)
        Collections.sort(uniqWordsToSort);
        //Компаратор по длине слова
        Collections.sort(uniqWordsToSort, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() < o2.length() ? -1 : (o1.length() == o2.length() ? 0 : 1));
            }
        });

        for (String s : uniqWordsToSort) {
            System.out.println(s);
        }
    }

    //3) Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле
    public void printFrequencyUniqueWords () {
        Set<String> uniqWords = new HashSet<>(Arrays.asList(wordArray));

        for (String uniqWord : uniqWords) {
            System.out.println(uniqWord + ": " + Collections.frequency(Arrays.asList(wordArray), uniqWord));
        }
    }

    //4) Выведите на экран все строки файла в обратном порядке
    public void printReversedLines() {
        ArrayList<String> lines = new ArrayList<>();
        for (String s : text.split("\\r?\\n")) { //\r может отсутствовать на unix-like)
            lines.add(s);
        }

        Collections.reverse(lines);

        for (String line : lines) {
            System.out.println(line);
        }
    }

    //5) Реализуйте свой iterator для обхода списка в обратном порядке
    public void printReversedWordList () {
    }

    //6) Выведите на экран строки, номера которых задаются пользователем в произвольном порядке
    public void printLine(int[] numbers) {
        //Тут лучше бы подошел обычный массив или ArrayList, но нужно и HashMap попробовать
        Map<Integer, String> lines = new HashMap<>();

        Integer lineNumber = new Integer(1); //Номера строк для удобства считаем с 1

        for (String s : text.split("\\r?\\n")) { //\r может отсутствовать на unix-like)
            lines.put(lineNumber, s);
            lineNumber++;
        }

        for (int number : numbers) {
            System.out.println(number + ": " + lines.get(number));
        }
    }
}
