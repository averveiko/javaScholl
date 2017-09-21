package ru.sbt.averveyko.collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ProcessText {
    private final String fileName;

    public ProcessText(String fileName) {
        this.fileName = fileName;
    }

    //Задание 1: Подсчитайте количество различных слов в файле.
    public void printWordsCount() throws IOException {
        long count = Files.lines(Paths.get(this.fileName))
                .map(String::toLowerCase)
                .map(str -> str.split("\\s+"))
                .flatMap(Arrays::stream)
                .distinct()
                .count();

        System.out.println(count);
    }

    //Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту)
    public void printSortedUniqueWords() throws IOException {
        Files.lines(Paths.get(this.fileName))
                .map(String::toLowerCase)
                .map(str -> str.split("\\s+"))
                .flatMap(Arrays::stream)
                .sorted()
                .distinct()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);
    }

    //Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле
    public void printFrequencyUniqueWords() throws IOException {
        List<String> allWords = Files.lines(Paths.get(this.fileName))
                .map(String::toLowerCase)
                .map(str -> str.split("\\s+"))
                .flatMap(Arrays::stream)
                .collect(toList());

        allWords.stream()
                .distinct()
                .forEach(str -> {
                    System.out.println(str + ": " + Collections.frequency(allWords, str));
                });
    }

    //Задание 4: Выведите на экран все строки файла в обратном порядке.
    public void printReversedLines() throws IOException {
        Files.lines(Paths.get(this.fileName))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator().forEachRemaining(System.out::println);
    }
    //Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
    //Это уже делалось

    //Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке
    public void printLine(int[] numbers) throws IOException {
        List<String> lines = Files.lines(Paths.get(this.fileName)).collect(toList());

        Arrays.stream(numbers)
                .forEach(i -> System.out.println(lines.get(i)));
    }
}
