package CommandLine;

import java.util.Random;
import java.util.Scanner;

public class Game {
    protected int turns;
    protected int score;
    public Game() {
        turns = 5;
        score = 0;
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
     * get random word index from 0 to 100.
     *
     * @param dictionary dictionary
     * @return Word.
     */
    public Word getRandomWord(Dictionary dictionary) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(101);
        return dictionary.getWord(randomIndex);
    }

}
