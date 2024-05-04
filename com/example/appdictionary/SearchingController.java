package com.example.appdictionary;

import com.example.appdictionary.commandline.DictionaryManagement;
import com.example.appdictionary.commandline.MainDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SearchingController extends HomeController implements Initializable {
    private final String PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\dictionaries.txt";
    private final String HISTORY_PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\HistoryWord.txt";

    private final String FAVORITE_PATH = "D:\\laptrinh\\AppDictionary\\src\\main\\resources\\com\\example\\appdictionary\\File\\Favorite.txt";

    @FXML
    private AnchorPane main;

    @FXML
    public TextField searchArea;

    @FXML
    public Button editButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Label englishDisplayWord;

    @FXML
    private TextArea explanation;

    @FXML
    public Button speakButton;

    @FXML
    private ListView<String> wordList;

    @FXML
    private int wordIndex;

    @FXML
    private MainDictionary mainDictionary = new MainDictionary();

    @FXML
    private MainDictionary _mainDictionary = new MainDictionary();

    @FXML
    private MainDictionary favoriteMainDictionary = new MainDictionary();

    @FXML
    private DictionaryManagement favoriteManagement = new DictionaryManagement(favoriteMainDictionary);

    @FXML
    private DictionaryManagement management = new DictionaryManagement(mainDictionary);

    @FXML
    public DictionaryManagement _management = new DictionaryManagement(_mainDictionary);

    @FXML
    ObservableList<String> res = FXCollections.observableArrayList();

    @FXML
    private WebView wExplanation;

    @FXML
    public TreeMap<String, Integer> mp = new TreeMap<>();

    @FXML
    private Button favButton;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        explanation.setEditable(false);
        management.Import(PATH);
        _management.Import(HISTORY_PATH);
        favoriteManagement.Import(FAVORITE_PATH);
        setDefaultList();
        searchArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTyped();
            }
        });
    }



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

    @FXML
    private void handleChooseWord() {
        try {
            if (!res.isEmpty()) {
                String str = wordList.getSelectionModel().getSelectedItem();
                wordIndex = mainDictionary.searchWordFromList(str);
                englishDisplayWord.setText(str);
                explanation.setText(mainDictionary.lookupWord(str));
                updateWebView(wExplanation, mainDictionary.lookupWord(str));
                if (!mp.containsKey(englishDisplayWord.getText())) {
                    mp.put(englishDisplayWord.getText(), 1);
                    _management.AddWordToHistoryFile(englishDisplayWord.getText(), explanation.getText());
                }
            }
        } catch (NullPointerException nullPtr) {
            nullPtr.getMessage();
        }
    }

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

    @FXML
    private void handleEditButton() {
        System.out.println("Edit Button Clicked!");
        explanation.setEditable(true);
        try {
            URL resource = getClass().getResource("/com/example/appdictionary/FXML/EditScreen.fxml");
            if (resource == null) {
                System.err.println("Cannot find the URL!");
                System.exit(1);
            }
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root, 831, 584);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit Word");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateWebView(wExplanation, mainDictionary.lookupWord(englishDisplayWord.getText()));
    }

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
                englishDisplayWord.setText(mainDictionary.getWord(wordIndex + 1).getWord());
                explanation.setText(mainDictionary.getWord(wordIndex + 1).getWord());
                updateWebView(wExplanation, mainDictionary.getWord(wordIndex + 1).getWord());
                refreshListWord();
            }
        });
        management.RemoveWordGUI(mainDictionary.getWord(wordIndex).getWord());
    }

    @FXML
    private void handleFavoriteButton() {
        showConfirm();
    }

    private void addToFile() {
        String engWord = englishDisplayWord.getText();
        String meaning = mainDictionary.lookupWord(engWord);
        favoriteManagement.addWordToFavoriteFile(engWord, meaning);
        System.out.println("You have added the word: " + engWord + " - " + meaning + " to your favorite list!");
    }

    public void showConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding Word To The Favorite Confirmation");
        alert.setHeaderText("Are you sure to add this word to your favorite list?");
        alert.setContentText("Choose your option");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                addToFile();
            } else {
                System.out.println("You have cancelled adding the word to your favorite list!");
            }
        });
    }

    private void setDefaultList() {
        ArrayList<String> words = mainDictionary.searching("", true);
        res.addAll(words);
        wordList.setItems(res);
    }

    private void refreshListWord() {
        for (int i = 0; i < res.size(); ++i) {
            if (res.get(i).equals(mainDictionary.getWord(wordIndex).getWord())) {
                res.remove(i);
                break;
            }
        }
        wordList.setItems(res);
    }

    private void updateWebView(WebView webView, String s) {
        webView.getEngine().loadContent(s);
    }
}
