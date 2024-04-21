package CommandLine;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private int turns;
    public Game() {
        turns = 5;
    }

    public int getTurns() {
        return turns;
    }
    public void setTurns(int turns) {
        this.turns = turns;
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
    /**
     * Rule
     */

    public void playGame(Word word) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Give meaning of this word in Vietnamese: " + word.getWordTarget());
        while (this.turns > 0) {
            System.out.print("Your answer:");
            String ans = sc.nextLine();
            if (ans.equals(word.getWordExplain())) {
                System.out.println("Correct");
                break;
            }
            else {
                System.out.println("Try again!");
            }
            this.turns -= 1;
        }
        if (this.turns == 0) {
            System.out.println("You have no turn left \n The answer is: " + word.getWordExplain());
        }
    }
}
