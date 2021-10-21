package com.example.dictionary_ver3;

public class Word {

    private String wordTarget; //từ
    private String wordExplain; //nghĩa

    Word() {
    }

    Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void getWordInfo(int i) {
        System.out.format("%-8s %-1s %-30s %-1s %-40s\n", (i + 1), "|", getWordTarget(), "|", getWordExplain());
    }
}
