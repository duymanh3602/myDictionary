package com.example.dictionary_ver3;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public static List<Word> words = new ArrayList<Word>();
    public static void addNewWord(Word word) {
        words.add(word);
    }
}
