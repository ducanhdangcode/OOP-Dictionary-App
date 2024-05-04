package CommandLine;

import java.util.Scanner;

public class GivingWord extends Game {
    //Đưa ra từ tiếng Anh có nghĩa cho trước
    public GivingWord(Dictionary dictionary) {
        super(dictionary);
        this.word = this.getRandomWord();
        this.turns = 5;
        this.score = 0;
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println( "Give the word which mean: " + this.word.getWordExplain());
        while (this.turns > 0) {
            System.out.print("Your answer: ");
            String ans = sc.nextLine();
            if (ans.equals(this.word.getWordTarget())) {
                System.out.println("Correct!");
                break;
            }
            else {
                this.turns --;
                System.out.println("Try again!");
            }
        }
        if (this.turns == 0) System.out.println("You lose! The correct answer is: " + this.word.getWordTarget());
    }
}
