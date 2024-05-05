package com.example.appdictionary.commandline;

import com.example.appdictionary.commandline.Game;

import java.util.Dictionary;
import java.util.Scanner;

public class GivingWord extends Game {
    //Đưa ra từ tiếng Anh có nghĩa cho trước
    public GivingWord(MainDictionary dictionary) {
        super(dictionary);
        this.wordManagement = this.getRandomWord();
        this.turns = 5;
        this.score = 0;
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println( "Give the word which mean: " + this.wordManagement.getWordExplain());
        while (this.turns > 0) {
            System.out.print("Your answer: ");
            String ans = sc.nextLine();
            if (ans.equals(this.wordManagement.getWord())) {
                System.out.println("Correct!");
                break;
            }
            else {
                this.turns --;
                System.out.println("Try again!");
            }
        }
        if (this.turns == 0) System.out.println("You lose! The correct answer is: " + this.wordManagement.getWord());
    }
}
