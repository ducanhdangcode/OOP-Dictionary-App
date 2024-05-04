package com.example.appdictionary;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController extends HomeController implements Initializable {
    @FXML
    private ImageView FindTheMeaningImage;

    @FXML
    private AnchorPane main;

    @FXML
    private ImageView HangManImage;

    /**
     * handle when initializing.
     * @param url - default param.
     * @param resourceBundle - default param.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FindTheMeaningImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                display("/com/example/appdictionary/FXML/FindTheMeaning.fxml");
            }
        });

        HangManImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                display("/com/example/appdictionary/FXML/HangMan.fxml");
            }
        });
    }

    /**
     * function to set the child of the main anchor pane.
     * @param node - children node of the anchor pane.
     */
    @FXML
    public void setChild(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    /**
     * function to display scene based on the path.
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
