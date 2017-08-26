package com.sbt.javaschool.averveyko;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 26.08.2017.
 */
public class ProcessText {
    
    private final String text;
    private String[] wordArray;
    
    public ProcessText(String text) {
        this.text = text;
        this.wordArray = text.split("\\s+");
    }

    //Всего слов в файле
    public int allWordsCount () {
        return wordArray.length;
    }

    //Подсчитайте кол-во различных слов в файле
    public int uniqueWordsCount () {
        
        Set<String> words = new HashSet<>();
        for (String s : wordArray) {
            words.add(s.toLowerCase());
        }

        return words.size();
    }
}
