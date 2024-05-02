package com.example.appdictionary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HangManGameOverController extends GameController implements Initializable {
    @FXML
    private AnchorPane main;

    @FXML
    private Button restartButton;

    @FXML
    private Button quitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                restart();
            }
        });

        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                quit();
            }
        });
    }

    private void restart() {
        System.out.println("You have restarted the game!");
        display("/com/example/appdictionary/FXML/HangMan.fxml");
    }

    private void quit() {
        System.out.println("You have quit the game!");
        display("/com/example/appdictionary/FXML/HomeApp.fxml");
    }
}
