package com.example.dictionary_ver3;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCommandline {

    public static List<Word> listOfWords = Dictionary.words;

    public static void showAllWords() {
        System.out.format("%-8s %-32s %-40s\n", "No", "|Vietnamese", "|English");
        for (int i = 0; i < listOfWords.size(); i++) {
            listOfWords.get(i).getWordInfo(i);
        }
    }

    /*
    public void dictionaryBasic() {
        System.out.println("Show all words in my Dictionary: ");
        this.showAllWords();
    }

    public void dictionaryAdvanced(String word) {
        DictionaryManagement.insertFromFile();
        showAllWords();
        DictionaryManagement.dictionaryLookup(word);
    }
     */

    public static boolean searchWord(String word) {
        for (int i=0;i<listOfWords.size();i++) {
            if (word.toLowerCase().equals(listOfWords.get(i).getWordTarget().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static List dictionarySearcher(String word) {
        List<String> searchList = new ArrayList<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            if ((listOfWords.get(i).getWordTarget()).toLowerCase().indexOf(word.toLowerCase()) == 0) {
                searchList.add(listOfWords.get(i).getWordTarget());
            }
        }
        return searchList;
    }
}
