package com.example.superdictionaryproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display("/com/example/superdictionaryproject/FXML/Searching.fxml");
    }

    @FXML
    private void setChild(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    @FXML
    private void display(String path) {
        try {
            AnchorPane c = FXMLLoader.load(getClass().getResource(path));
            setChild(c);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
