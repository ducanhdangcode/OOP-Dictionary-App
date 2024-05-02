package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HangManController extends GameController implements Initializable {
    private final String PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplainNew.txt";

    private final String ScoreRecordPath = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\HangManScore.txt";

    private final String correctGuessString = "FANTASTIC GUESS! THERE IS/ARE LETTER(S) IN THE MYSTERY WORD!";

    private final String wrongGuessString = "SORRY! THERE IS/ARE NOT ANY LETTER IN THE MYSTERY WORD!";

    private final String loseGameString = "YOU LOSE! PRESS RESTART BUTTON TO RESTART THE GAME!";

    private final String correctWord = "WONDERFUL GUESS! YOU HAVE REVEALED THE MYSTERY WORD!";

    @FXML
    private AnchorPane main;

    @FXML
    private Label explainWord;

    @FXML
    private HBox guessLettersBox;

    @FXML
    private TextField searchArea;

    @FXML
    private Button buttonCheck;

    @FXML
    private ImageView monster1;

    @FXML
    private ImageView monster2;

    @FXML
    private ImageView monster3;

    @FXML
    private ImageView monster4;

    @FXML
    private ImageView monster5;

    @FXML
    private ImageView monster6;

    @FXML
    private ImageView monster7;

    @FXML
    private ImageView monster8;

    @FXML
    private ImageView monster9;

    @FXML
    private Label guessState;

    @FXML
    private Button exitButton;

    @FXML
    private Button restartButton;

    @FXML
    private Label revealLabel;

    @FXML
    private Label stageLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label turnLabel;

    @FXML
    private Label highScoreLabel;

    private MainDictionary mainDictionary = new MainDictionary();

    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    private String str = "";

    private char[] guessedLetters;

    private int turns;

    private int score = 0;

    private int stage = 0;

    private List<Integer> scoreStore = new ArrayList<>();

    /**
     * handle when initializing.
     * @param url - default param.
     * @param resourceBundle - default param.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        turns = 8;
        management.Import(PATH);
        String defaultScore = "SCORE: 0";
        String defaultTurn = "TURN: 8";
        scoreLabel.setText(defaultScore);
        turnLabel.setText(defaultTurn);
        setTheRandomMeaning();
        setDefaultGuessedChar();
        resetLabelForWord();
        displayHighScore();
        buttonCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkGuess();
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                display("/com/example/appdictionary/FXML/HomeApp.fxml");
            }
        });

        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scoreStore.add(score);
                for (int sc : scoreStore) {
                    System.out.print(sc + "->");
                }
                ExportScoreFile(scoreStore, ScoreRecordPath);
                displayHighScore();
                main.setStyle("-fx-background-color: #ffffff");
                display("/com/example/appdictionary/FXML/HangMan.fxml");
            }
        });
    }

    /**
     * function to set the random meaning word for user to guess.
     */
    private void setTheRandomMeaning() {
        ++stage;
        String stageString = "STAGE: " + stage;
        stageLabel.setText(stageString);
        Random rand = new Random();
        int randIdx = rand.nextInt(8360);
        str = mainDictionary.getWord(randIdx).getWord();
        String meaning = management.LookupWord(str);
        explainWord.setText(meaning);
    }

    /**
     * function to set the default guessed characters.
     */
    private void setDefaultGuessedChar() {
        int sz = str.length();
        guessedLetters = new char[sz];
        for (int i = 0; i < sz; ++i) {
            guessedLetters[i] = '_';
        }
    }

    /**
     * function to handle when user make a guess.
     * @param letter - letter which player want to guess.
     * @return true if there is/are a letter from mystery word.
     */
    private boolean makeGuess(char letter) {
        boolean checkGuess = false;
        int sz = str.length();
        for (int i = 0; i < sz; ++i) {
            if (str.charAt(i) == letter) {
                guessedLetters[i] = letter;
                checkGuess = true;
            }
        }
        return checkGuess;
    }

    /**
     * function to display a guessed letters on the screen.
     */
    public void displayGuessLetters() {
        ObservableList<Node> children = guessLettersBox.getChildren();
        int idx = 0;
        for (Node node : children) {
            if (node instanceof Label) {
                ((Label) node).setText("" + guessedLetters[idx]);
                ++idx;
            }
        }
    }

    /**
     * function to handle the answer of user.
     */
    public void checkGuess() {
        char c = searchArea.getText().charAt(0);
        if (makeGuess(c)) {
            score += 10;
            String scoreString = "SCORE: " + score;
            scoreLabel.setText(scoreString);
            guessState.setText(correctGuessString);
        } else {
            --turns;
            String turnString = "TURN: " + turns;
            turnLabel.setText(turnString);
            guessState.setText(wrongGuessString);
        }
        displayGuessLetters();
        updateState();
        searchArea.setText("");

        if (checkEqual()) {
            manageNextWord();
        }
    }

    /**
     * update GUI state of the game.
     */
    private void updateState() {
        switch (turns) {
            case 8:
                monster1.setVisible(true);
                break;
            case 7:
                monster1.setVisible(false);
                monster2.setVisible(true);
                break;
            case 6:
                monster2.setVisible(false);
                monster3.setVisible(true);
                break;
            case 5:
                monster3.setVisible(false);
                monster4.setVisible(true);
                break;
            case 4:
                monster4.setVisible(false);
                monster5.setVisible(true);
                break;
            case 3:
                monster5.setVisible(false);
                monster6.setVisible(true);
                break;
            case 2:
                monster6.setVisible(false);
                monster7.setVisible(true);
                break;
            case 1:
                monster7.setVisible(false);
                monster8.setVisible(true);
                break;
            case 0:
                monster8.setVisible(false);
                monster9.setVisible(true);
                manageLose();
                break;
            default:
                break;
        }
    }

    /**
     * function to check if the word that user guess is equal to the mystery word or not.
     * @return
     */
    private boolean checkEqual() {
        String res = "";
        for (char letter : guessedLetters) {
            res += letter;
        }
        if (res.equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * function to display next word when user answer correctly.
     */
    private void manageNextWord() {
        score += 15;
        String scoreString = "SCORE: " + score;
        scoreLabel.setText(scoreString);
        setTheRandomMeaning();
        int newSz = str.length();
        guessedLetters = new char[newSz];
        for (int i = 0; i < newSz; ++i) {
            guessedLetters[i] = '_';
        }
        guessState.setText(correctWord);
        resetLabelForWord();
    }

    /**
     * function to reset the label when next word appeared.
     */
    private void resetLabelForWord() {
        guessLettersBox.getChildren().clear();
        int sz = str.length();
        for (int i = 0; i < sz; ++i) {
            char c = guessedLetters[i];
            Label label = new Label(String.valueOf(c));
            label.setStyle("-fx-font-size: 23; -fx-font-family: Lucida Sans Typewriter; -fx-font-weight: bold; -fx-border-color: red;" +
                    "-fx-border-width: 2; -fx-border-radius: 10; -fx-alignment: center;");
            label.setLayoutX(100 + i * 40);
            label.setLayoutY(50);
            label.setPrefWidth(35);
            guessLettersBox.getChildren().add(label);
        }
    }

    /**
     * function to handle when user lose the game.
     */
    private void manageLose() {
        restartButton.setVisible(true);
        guessState.setText(loseGameString);
        String revealString = "THE MYSTERY WORD IS: " + str;
        revealLabel.setText(revealString);
    }

    /**
     * function to store the score each play time.
     * @param store - score array to store.
     * @param path - path of the score file.
     */
    private void ExportScoreFile(List<Integer> store, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int sc : store) {
                String tmp = Integer.toString(sc);
                bufferedWriter.write(tmp);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Exception Occur: " + e.getMessage());
        }
    }

    /**
     * function to get high score from the score file.
     * @param path - path of the score file.
     * @return the high score from score file.
     */
    private int GetHighScore(String path) {
        int highScore = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                try {
                    int currentScore = Integer.parseInt(line.trim());
                    if (currentScore > highScore) {
                        highScore = currentScore;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format: " + e.getMessage());
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Exception Occur: " + e.getMessage());
        }
        return highScore;
    }

    /**
     * function to display high score to the screen.
     */
    private void displayHighScore() {
        int highScore = GetHighScore(ScoreRecordPath);
        System.out.println("High Score: " + highScore);
        highScoreLabel.setText("HIGH SCORE: " + highScore);
    }
}
