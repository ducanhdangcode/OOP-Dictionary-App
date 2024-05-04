package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EditScreenController implements Initializable {
    private String PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\dictionaries.txt";

    @FXML
    private TextField typeWordArea;

    @FXML
    private TextArea oldMeaning;

    @FXML
    private WebView wOldMeaning;

    @FXML
    private ListView<String> wordList;

    @FXML
    ObservableList<String> res = FXCollections.observableArrayList();

    @FXML
    private MainDictionary mainDictionary = new MainDictionary();

    @FXML
    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    @FXML
    private Label englishDisplayWord;

    @FXML
    private Button editWord;

    @FXML
    private int wordIndex;

    @FXML
    private TextField NewMeaning;

    /**
     * handle when initializing.
     * @param url - default params.
     * @param resourceBundle - default params.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        oldMeaning.setEditable(false);
        management.Import(PATH);
        setDefaultList();
        typeWordArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTyped();
            }
        });
    }

    /**
     * handle when typing text into text field.
     * this function will display the suggestion for the user.
     */
    @FXML
    private void handleTyped() {
        String str = typeWordArea.getText();
        ArrayList<String> suggest = mainDictionary.searching(str, false);
        if (suggest.isEmpty()) {
            res.clear();
        } else {
            res = FXCollections.observableArrayList();
            res.addAll(suggest);
            wordList.setItems(res);
        }
    }

    /**
     * handle when user choose a word from suggestion list.
     */
    @FXML
    private void handleChooseWord() {
        try {
            if (!res.isEmpty()) {
                String str = wordList.getSelectionModel().getSelectedItem();
                wordIndex = mainDictionary.searchWordFromList(str);
                englishDisplayWord.setText(str);
                oldMeaning.setText(mainDictionary.lookupWord(str));
                updateWebView(wOldMeaning, mainDictionary.lookupWord(str));
            }
        } catch (NullPointerException nullPtr) {
            nullPtr.getMessage();
        }
    }

    /**
     * function to edit the word: replace old meaning by new meaning typed by user.
     */
    @FXML
    private void handleEditWord() {
        try {
            String str = NewMeaning.getText();
            System.out.println(str);
            management.UpdateWordGUI(englishDisplayWord.getText(), str);
        } catch (NullPointerException nullPtr) {
            nullPtr.getMessage();
        }
    }

    /**
     * set default suggestion list when display an edit screen.
     */
    private void setDefaultList() {
        ArrayList<String> words = mainDictionary.searching("", true);
        res.addAll(words);
        wordList.setItems(res);
    }

    /**
     * function to show confirm window from the user.
     * if user want to edit, handle it.
     * else back to the edit screen.
     */
    public void showConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Edit word to the new meaning!");
        alert.setHeaderText("Dou you want to edit this word?");
        alert.setContentText("Choose your option");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                handleEditWord();
            } else {
                System.out.println("You have cancelled editing the word!");
            }
        });
    }
    private void updateWebView(WebView webView, String s) {
        webView.getEngine().loadContent(s);
    }
}
