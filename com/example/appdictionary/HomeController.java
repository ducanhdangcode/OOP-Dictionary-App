package com.example.appdictionary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane main;

    @FXML
    private Button searchButton;

    @FXML
    private Button translateButton;

    @FXML
    private Button addButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button favoriteButton;

    @FXML
    private Button gameButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        display("/com/example/appdictionary/FXML/Searching.fxml");

        // handle when click the search button.
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Search Button Clicked!");
                display("/com/example/appdictionary/FXML/Searching.fxml");
            }
        });

        // handle when click the translate button.
        translateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Translate Button Clicked!");
                display("/com/example/appdictionary/FXML/Translation.fxml");
            }
        });

        // handle when click the add button.
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Adding Button Clicked!");
                display("/com/example/appdictionary/FXML/Adding.fxml");
            }
        });

        // handle when click the history button.
        historyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("History Button Clicked!");
                display("/com/example/appdictionary/FXML/History.fxml");
            }
        });

        // handle when click the favorite button.
        favoriteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Favorite Button Clicked!");
                display("/com/example/appdictionary/FXML/Favorite.fxml");
            }
        });

        // handle when click the game button.
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Game Button Clicked!");
                display("/com/example/appdictionary/FXML/Game.fxml");
            }
        });
    }

    /**
     * function to set child of the anchor pane.
     * @param node - children node of the anchor pane.
     */
    @FXML
    public void setChild(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    /**
     * function to display an anchor pane through the path.
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
}