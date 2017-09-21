package ru.sbt.averveyko.collections;

import java.io.IOException;

public class Main {
    private static final String fileName = "text.txt";
    public static void main(String[] args) {
        ProcessText processText = new ProcessText(fileName);

        try {
            System.out.println("Количество различных слов в файле: ");
            processText.printWordsCount();
            System.out.println("Cписок различных слов файла: ");
            processText.printSortedUniqueWords();
            System.out.println("Cколько раз каждое слово встречается в файле:");
            processText.printFrequencyUniqueWords();
            System.out.println("Все строки файла в обратном порядке:");
            processText.printReversedLines();
            System.out.println("Строки, номера которых задаются пользователем в произвольном порядке:");
            int[] numbers = {49, 32, 20, 33};
            processText.printLine(numbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
