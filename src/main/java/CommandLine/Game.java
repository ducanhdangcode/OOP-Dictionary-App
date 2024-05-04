package CommandLine;

import java.util.*;

import java.util.Random;
import java.util.Scanner;

public abstract class Game {
    protected Dictionary dictionary;
    protected WordManagement wordManagement;
    protected int turns;
    protected int score;
    public Game(Dictionary dictionary) {
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
        return dictionary.wordManagement(randomIndex);
    }
    protected void play() {};
}
