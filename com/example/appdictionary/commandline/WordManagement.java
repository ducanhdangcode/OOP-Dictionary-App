package com.example.appdictionary.commandline;

public class WordManagement {
    private String word;
    private String wordExplain;

    public WordManagement() {

    }

    /**
     * constructor with 2 paras.
     * @param word - word of user.
     * @param wordExplain - meaning of word.
     */
    public WordManagement(String word, String wordExplain) {
        this.word = word;
        this.wordExplain = wordExplain;
    }

    public String getWord() {
        return this.word;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWord(String newWord) {
        this.word = newWord;
    }

    public void setWordExplain(String newWordExplain) {
        this.wordExplain = newWordExplain;
    }
}
