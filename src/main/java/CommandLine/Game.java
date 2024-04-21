package CommandLine;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Hangman hman;
    private int turns;
    public Game() {
        hman = new Hangman();
        turns = 7;
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
                switch (turns) {
                    case 7:
                        this.hman.L_Hold();
                        break;
                    case 6:
                        this.hman.hangHead();
                        break;
                    case 5:
                        this.hman.hangBody();
                        break;
                    case 4:
                        this.hman.hangArm1();
                        break;
                    case 3:
                        this.hman.hangArm2();
                        break;
                    case 2:
                        this.hman.hangLeg1();
                        break;
                    case 1:
                        this.hman.hangLeg2();
                        break;
                }
                System.out.println("Try again!");
            }
            this.turns -= 1;
        }
        if (this.turns == 0) {
            System.out.println("You have no turn left \n The answer is: " + word.getWordExplain());
        }
    }

}
