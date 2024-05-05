package com.example.appdictionary.commandline;

import java.util.*;

import java.util.Random;

public abstract class Game {
    protected MainDictionary dictionary;
    protected WordManagement wordManagement;
    protected int turns;
    protected int score;
    public Game(MainDictionary dictionary) {
        this.score = 0;
        this.turns = 6;
        this.dictionary = dictionary;
    }

    public int getTurns() {
        return turns;
    }
    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * get random word index from 0 to 1000.
     *
     * @return Word.
     */
    public WordManagement getRandomWord() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(10);
        return dictionary.getWord(randomIndex);
    }
    protected void play() {};
}