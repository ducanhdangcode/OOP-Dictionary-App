package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController extends HomeController implements Initializable {
    private static final String FAVORITE_PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Favorite.txt";

    @FXML
    private TextField searchArea;

    @FXML
    private ListView<String> wordList;

    @FXML
    private TextArea explanation;

    @FXML
    private WebView wExplanation;

    @FXML
    private Label englishDisplayWord;

    private int wordIndex;

    @FXML
    ObservableList<String> res = FXCollections.observableArrayList();

    @FXML
    private MainDictionary mainDictionary = new MainDictionary();

    @FXML
    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    /**
     * handle when initializing.
     * @param url - default param.
     * @param resourceBundle - default param.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        explanation.setEditable(false);
        management.Import(FAVORITE_PATH);
        setDefaultList();
        searchArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTyped();
            }
        });
    }

    /**
     * handle when user type text into text field.
     * function to display suggestion list.
     */
    @FXML
    private void handleTyped() {
        String str = searchArea.getText();
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
     * handle when user choose the word from the suggestion list.
     */
    @FXML
    private void handleChooseWord() {
        try {
            if (!res.isEmpty()) {
                String str = wordList.getSelectionModel().getSelectedItem();
                wordIndex = mainDictionary.searchWordFromList(str);
                englishDisplayWord.setText(str);
                explanation.setText(mainDictionary.lookupWord(str));
                updateWebView(wExplanation, mainDictionary.lookupWord(str));
            }
        } catch (NullPointerException nullPtr) {
            nullPtr.getMessage();
        }
    }

    /**
     * handle when user click the speak button.
     * a voice will speak the word.
     */
    @FXML
    private void handleClickSpeakButton() {
        System.out.println("Speak button clicked!");
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(englishDisplayWord.getText());
        } else throw new IllegalStateException("Can't find the voice!");
    }

    /**
     * handle delete, show confirm from the user to delete.
     * if user agree to delete, handle it.
     * else return to the delete screen.
     */
    @FXML
    private void handleDelete() {
        System.out.println("Delete Button Clicked!");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("DELETE CONFIRMATION");
        alert.setHeaderText("You will delete this word!");
        ButtonType yesButton = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("NO", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                if (mainDictionary.getWord(wordIndex + 1).getWord() != null) {
                    englishDisplayWord.setText(mainDictionary.getWord(wordIndex + 1).getWord());
                    explanation.setText(mainDictionary.getWord(wordIndex + 1).getWord());
                    updateWebView(wExplanation, mainDictionary.getWord(wordIndex + 1).getWord());
                    refreshListWord();
                } else {
                    englishDisplayWord.setText(mainDictionary.getWord(wordIndex - 1).getWord());
                    explanation.setText(mainDictionary.getWord(wordIndex - 1).getWord());
                    updateWebView(wExplanation, mainDictionary.getWord(wordIndex - 1).getWord());
                    refreshListWord();
                }
            }
        });
        management.RemoveWordFavorite(mainDictionary.getWord(wordIndex).getWord());
    }

    /**
     * set default suggestion list when delete screen displayed.
     */
    private void setDefaultList() {
        ArrayList<String> words = mainDictionary.searching("", true);
        res.addAll(words);
        wordList.setItems(res);
    }

    /**
     * refresh list word after deletion from user.
     */
    private void refreshListWord() {
        for (int i = 0; i < res.size(); ++i) {
            if (res.get(i).equals(mainDictionary.getWord(wordIndex).getWord())) {
                res.remove(i);
                break;
            }
        }
        wordList.setItems(res);
    }

    /**
     * update the webview after deletion from the user.
     * web view now will display the meaning of the following word.
     * @param webView - web view of the delete screen.
     * @param s - word to display
     */
    private void updateWebView(WebView webView, String s) {
        webView.getEngine().loadContent(s);
    }
}
