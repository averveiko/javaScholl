package com.sbt.javaschool.averveyko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static String TEXT_FILE_NAME = "text.txt"; //ожидается в кодировке UTF-8

    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get(TEXT_FILE_NAME);
        //Проверяем существует ли файл
        if ( ! Files.exists(filePath)) {
            System.err.println(TEXT_FILE_NAME + " not found.");
            return;
        }
        //Читаем файл в строку
        String content = new String(Files.readAllBytes(filePath));

        ProcessText pt = new ProcessText(content);

        System.out.println("Количество слов в файле: " + pt.allWordsCount());
        System.out.println("Количество различных слов в файле: " + pt.uniqueWordsCount());
        System.out.println();
        System.out.println("Cписок различных слов файла, отсортированный по возрастанию их длины:");
        pt.printSortedUniqueWords();
        System.out.println();
        System.out.println("Cколько раз каждое слово встречается в файле:");
        pt.printFrequencyUniqueWords();
        System.out.println();
        System.out.println("Все строки файла в обратном порядке:");
        pt.printReversedLines();

        pt.printReversedWordList();

        System.out.println();
        System.out.println("строки, номера которых задаются пользователем в произвольном порядке:");
        int[] numbers = {50, 33, 21, 34};
        pt.printLine(numbers);
    }
}
