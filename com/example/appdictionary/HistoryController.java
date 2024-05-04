package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController extends HomeController implements Initializable {
    private final String HISTORY_PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\HistoryWord.txt";

    @FXML
    public TextField SearchArea;

    @FXML
    private ListView<String> wordList;

    @FXML
    public TextArea explanation;

    @FXML
    public WebView wExplanation;

    @FXML
    public Label englishDisplayWord;


    private MainDictionary mainDictionary = new MainDictionary();

    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    @FXML
    ObservableList<String> res = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        explanation.setEditable(false);
        management.Import(HISTORY_PATH);
        setDefaultHistoryList();
        SearchArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTyped();
            }
        });
    }

    @FXML
    private void handleTyped() {
        String str = SearchArea.getText();
        ArrayList<String> suggest = mainDictionary.searching(str, false);
        if (suggest.isEmpty()) {
            res.clear();
        } else {
            res = FXCollections.observableArrayList();
            res.addAll(suggest);
            wordList.setItems(res);
        }
    }

    @FXML
    private void handleChooseWord() {
        try {
            if (!res.isEmpty()) {
                String str = wordList.getSelectionModel().getSelectedItem();
                englishDisplayWord.setText(str);
                explanation.setText(mainDictionary.lookupWord(str));
                updateWebView(wExplanation, mainDictionary.lookupWord(str));
            }
        } catch (NullPointerException nullPtr) {
            nullPtr.getMessage();
        }
    }

    private void setDefaultHistoryList() {
        ArrayList<String> words = mainDictionary.searching("", true);
        res.addAll(words);
        wordList.setItems(res);
    }

    private void updateWebView(WebView webView, String s) {
        webView.getEngine().loadContent(s);
    }
}
