package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class FindTheMeaningController extends GameController implements Initializable {
    private final String PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\WordAndExplainNew.txt";

    private final String correctAnswer = "Congratulations! Your answer is correct!";

    private final String ScoreRecordingPath = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\FindTheMeaningScore.txt";

    @FXML
    private Label englishWord;

    @FXML
    private Label meaningA;

    @FXML
    private Label meaningB;

    @FXML
    private Label meaningC;

    @FXML
    private Label meaningD;

    @FXML
    private Label stateAnswer;

    @FXML
    private Pane paneA;

    @FXML
    private Pane paneB;

    @FXML
    private Pane paneC;

    @FXML
    private Pane paneD;

    @FXML
    private Button nextQuestionButton;

    @FXML
    private Label stageLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private AnchorPane main;

    @FXML
    private Button exitButton;

    @FXML
    private Label highScoreLabel;

    private MainDictionary mainDictionary = new MainDictionary();

    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    private String str = "";
    private int randIdx = 0;

    private int stage = 0;

    private int score = 0;

    private List<Integer> storeScore = new ArrayList<>();

    /**
     * handle when initializing
     * @param url - default param.
     * @param resourceBundle - default param.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        management.Import(PATH);
        setTheRandomWord();
        setUpOptions();
        displayHighScore();

        // handle when click to the option A
        paneA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (meaningA.getText().equals(mainDictionary.lookupWord(englishWord.getText()))) {
                    manageCorrectAnswer();
                } else {
                    manageWrongAnswer();
                }
            }
        });

        // handle when click the option B
        paneB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (meaningB.getText().equals(mainDictionary.lookupWord(englishWord.getText()))) {
                    manageCorrectAnswer();
                } else {
                    manageWrongAnswer();
                }
            }
        });

        // handle when click the option C
        paneC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (meaningC.getText().equals(mainDictionary.lookupWord(englishWord.getText()))) {
                    manageCorrectAnswer();
                } else {
                    manageWrongAnswer();
                }
            }
        });

        // handle when click the option D
        paneD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (meaningD.getText().equals(mainDictionary.lookupWord(englishWord.getText()))) {
                    manageCorrectAnswer();
                } else {
                    manageWrongAnswer();
                }
            }
        });

        // handle when click the next question button
        nextQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayNextQuestion();
            }
        });

        // handle when click the exit button.
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exitToMainMenu();
            }
        });
    }

    /**
     * function to set the random word when starting the game.
     */
    private void setTheRandomWord() {
        stateAnswer.setText("");
        Random rand = new Random();
        randIdx = rand.nextInt(8390);
        str = mainDictionary.getWord(randIdx).getWord();
        englishWord.setText(str);
    }

    /**
     * create the random answer option for the game.
     */
    private void setUpOptions() {
        Random rand = new Random();
        int randIndex = rand.nextInt(4);
        int randIndex1 = rand.nextInt(8390);
        int randIndex2 = rand.nextInt(8390);
        int randIndex3 = rand.nextInt(8390);
        if (randIndex == 1) {
            meaningA.setText(management.LookupWord(str));
            meaningB.setText(management.LookupWord(mainDictionary.getWord(randIndex1).getWord()));
            meaningC.setText(management.LookupWord(mainDictionary.getWord(randIndex2).getWord()));
            meaningD.setText(management.LookupWord(mainDictionary.getWord(randIndex3).getWord()));
        } else if (randIndex == 2) {
            meaningB.setText(management.LookupWord(str));
            meaningA.setText(management.LookupWord(mainDictionary.getWord(randIndex1).getWord()));
            meaningC.setText(management.LookupWord(mainDictionary.getWord(randIndex2).getWord()));
            meaningD.setText(management.LookupWord(mainDictionary.getWord(randIndex3).getWord()));
        } else if (randIndex == 3) {
            meaningC.setText(management.LookupWord(str));
            meaningA.setText(management.LookupWord(mainDictionary.getWord(randIndex1).getWord()));
            meaningB.setText(management.LookupWord(mainDictionary.getWord(randIndex2).getWord()));
            meaningD.setText(management.LookupWord(mainDictionary.getWord(randIndex3).getWord()));
        } else {
            meaningD.setText(management.LookupWord(str));
            meaningA.setText(management.LookupWord(mainDictionary.getWord(randIndex1).getWord()));
            meaningB.setText(management.LookupWord(mainDictionary.getWord(randIndex2).getWord()));
            meaningC.setText(management.LookupWord(mainDictionary.getWord(randIndex3).getWord()));
        }
    }

    /**
     * function to handle when user answer correctly.
     */
    private void manageCorrectAnswer() {
        score += 15;
        scoreLabel.setText(Integer.toString(score));
        stateAnswer.setText(correctAnswer);
    }

    /**
     * function to display next question when user answer correctly.
     */
    private void displayNextQuestion() {
        ++stage;
        stageLabel.setText(Integer.toString(stage));
        setTheRandomWord();
        setUpOptions();
    }

    /**
     * function to handle when user answer wrong.
     */
    private void manageWrongAnswer() {
        String incorrectAnswer = "Sorry! Your answer is incorrect! The correct answer is: "
                + management.LookupWord(str);
        stateAnswer.setText(incorrectAnswer);
        showConfirm();
    }

    /**
     * function to confirm if user want to restart the game or not.
     */
    private void showConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Restart \"Find The Meaning\"");
        alert.setHeaderText("Do you want to restart the game?");
        alert.setContentText("Choose your option");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.out.println("You have restarted Find The Meaning game!");
                restartGame();
            } else {
                System.out.println("You have quit the game!");
                exitToMainMenu();
            }
        });
    }

    /**
     * handle when user agree to restart the game.
     */
    private void restartGame() {
        storeScore.add(score);
        for (int sc : storeScore) {
            System.out.println(sc + "->");
        }
        ExportScoreFile(storeScore, ScoreRecordingPath);
        displayHighScore();
        score = 0;
        stage = 1;
        scoreLabel.setText(Integer.toString(score));
        stageLabel.setText(Integer.toString(stage));
        setTheRandomWord();
        setUpOptions();
    }

    /**
     * set child of the main anchor pane.
     * @param node - children node of the anchor pane.
     */
    @FXML
    public void setChild(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    /**
     * display a scene base on the path.
     * @param path - path of the anchor pane to be presented.
     */
    @FXML
    public void display(String path) {
        try {
            AnchorPane c = FXMLLoader.load(getClass().getResource(path));
            setChild(c);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function to handle when user want to exit to the main menu.
     */
    private void exitToMainMenu() {
        display("/com/example/appdictionary/FXML/HomeApp.fxml");
    }

    /**
     * handle to store to score of each play time.
     * @param store
     * @param path
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
     * @return high score of the file.
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
     * function to display the high score to the screen.
     */
    private void displayHighScore() {
        int highScore = GetHighScore(ScoreRecordingPath);
        System.out.println("High Score: " + highScore); // debug
        highScoreLabel.setText(Integer.toString(highScore));
    }
}

