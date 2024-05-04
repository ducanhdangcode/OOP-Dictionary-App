package CommandLine;

import java.util.Scanner;

public class Hangman extends Game {
    private char[] guessedLetters;
    public Hangman(Dictionary dictionary) {
        super(dictionary);
        this.turns = 6;
        this.word = getRandomWord();
        guessedLetters = new char[word.getWordTarget().length()];
        initializeGuessedLetters();
    }
    public void L_Hold() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |");
        System.out.println(" |");
        System.out.println(" |");
    }

    public void hangHead() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |");
        System.out.println(" |");
    }

    public void hangBody() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |    |");
        System.out.println(" |");
    }

    public void hangArm1() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |   /|");
        System.out.println(" |");
    }
    public void hangArm2() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |   /|\\");
        System.out.println(" |");
    }
    public void hangLeg1() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |   /|\\");
        System.out.println(" |   /");
    }
    public void hangLeg2() {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    O");
        System.out.println(" |   /|\\");
        System.out.println(" |   / \\");
        System.out.println("Game over! The word was: " + this.word.getWordTarget());
    }
    public boolean isHangMan() {
        return this.turns == 0;
    }

    private void initializeGuessedLetters() {
        for (int i = 0; i < this.word.getWordTarget().length(); i++) {
            guessedLetters[i] = '_';
        }
    }

    private void displayProgress() {
        System.out.println("Current progress: " + new String(guessedLetters));
    }

    private void displayHangman() {
        //int remainingAttempts = this.turns; // Số lần còn lại trước khi thua
        System.out.println("Attempts left: " + (this.turns + 1));
        switch (this.turns + 1) {
            case 6:
                L_Hold();
                break;
            case 5:
                hangHead();
                break;
            case 4:
                hangBody();
                break;
            case 3:
                hangArm1();
                break;
            case 2:
                hangArm2();
                break;
            case 1:
                hangLeg1();
                break;
        }
    }

    public boolean checkCorrect() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.guessedLetters.length; ++i){
            s.append(this.getGuessedLetters()[i]);
        }
        if (s.toString().equals(this.getWord().getWordTarget())) return true;
        return false;
    }

    private boolean isGameOver() {
        return this.turns + 1 == 0 || new String(guessedLetters).equals(this.word.getWordTarget());
    }

    public char[] getGuessedLetters(){
        return this.guessedLetters;
    }
    //Đoán ký tự
    public boolean makeGuess(char letter) {
        boolean correctGuess = false;
        for (int i = 0; i < this.word.getWordTarget().length(); i++) {
            if (this.word.getWordTarget().charAt(i) == letter) {
                guessedLetters[i] = letter;
                correctGuess = true;
            }
        }
        if (!correctGuess) {
            this.turns--;
        }
        return correctGuess;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.word.getWordExplain());
        while (!isGameOver()) {
            displayProgress();
            displayHangman();

            System.out.print("Enter a letter: ");
            char guess = scanner.next().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("Please enter a valid letter.");
                continue;
            }

            if (makeGuess(guess)) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Incorrect guess.");
            }
            if (this.turns + 1== 0) hangLeg2();
        }

    }
}
