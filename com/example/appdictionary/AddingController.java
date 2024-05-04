package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddingController implements Initializable {
    private final String PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\dictionaries.txt";

    @FXML
    private TextField engTextField;

    @FXML
    private TextField vieTextField;

    @FXML
    private Button addingButton;

    private MainDictionary mainDictionary = new MainDictionary();

    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    /**
     * function to handle when initializing.
     * @param url - default params.
     * @param resourceBundle - default params.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        management.Import(PATH);
        addingButton.setOnAction(event -> showConfirm());
    }

    /**
     * function to add the word and its meaning to the file.
     */
    private void addToFile() {
        String engWord = engTextField.getText();
        String meaning = vieTextField.getText();
        management.AddWordGUI(engWord, meaning);
        System.out.println("You have added the word: " + engWord + " - " + meaning + " successfully!");
    }

    /**
     * function to show confirm window from user.
     * if user want to add, add to the file.
     * else return to the window of adding screen.
     */
    public void showConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding Word Confirmation");
        alert.setHeaderText("Are you sure to add this word?");
        alert.setContentText("Choose your option");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                addToFile();
            } else {
                System.out.println("You have cancelled adding the word!");
            }
        });
    }
}
