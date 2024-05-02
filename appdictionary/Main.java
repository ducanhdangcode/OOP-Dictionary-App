package com.example.appdictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            URL resource = getClass().getResource("/com/example/appdictionary/FXML/HomeApp.fxml");
            if (resource == null) {
                System.err.println("Cannot find the URL!");
                System.exit(1);
            }
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root, 870, 600);
            stage.setScene(scene);
            stage.setTitle("Super Dictionary");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
