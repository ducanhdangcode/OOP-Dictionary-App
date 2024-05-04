package com.example.superdictionaryproject;

import CommandLine.Dictionary;
import CommandLine.Hangman;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class HangmanController extends GameController {
    @FXML
    private TextField wordField;
    @FXML
    private TextField turnsField;
    @FXML
    private TextField guessedLettersField;
    @FXML
    private TextField guessField;
    @FXML
    private Button guessButton;

    private Hangman hangman;

    public void initialize() {
        // Tạo một trò chơi Hangman mới
        hangman = new Hangman(new Dictionary());

        // Cập nhật giao diện người dùng
        updateUI();

        // Xử lý sự kiện khi nút được nhấn
        guessButton.setOnAction(e -> {
            String guess = guessField.getText();
            if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
                hangman.makeGuess(guess.charAt(0));
                updateUI();
                guessField.clear();
            }
        });
    }

    private void updateUI() {
        wordField.setText(hangman.getWord().getWordExplain());
        turnsField.setText("Turns left: " + hangman.getTurns());
        guessedLettersField.setText("Guessed letters: " + Arrays.toString(hangman.getGuessedLetters()));
    }
}
