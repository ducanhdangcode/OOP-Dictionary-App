package com.example.appdictionary;

import com.example.appdictionary.commandline.Translate;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslationController extends HomeController implements Initializable {
    @FXML
    private TextArea inputTextZone;

    @FXML
    private TextArea outputTextZone;

    @FXML
    private Button translateButton;

    @FXML
    private Label englishLabel;

    @FXML
    private Label vietnameseLabel;

    @FXML
    private boolean ok = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputTextZone.setEditable(false);
        translateButton.setOnAction(this::handleTranslateButton);
    }

    @FXML
    private void handleTranslateButton(ActionEvent event) {
        String toTranslateText = inputTextZone.getText();
        try {
            if (englishLabel.getLayoutX() == 71.0) {
                String translatedText = Translate.translate("en", "vi", toTranslateText);
                outputTextZone.setText(translatedText);
            } else {
                String translatedText = Translate.translate("vi", "en", toTranslateText);
                outputTextZone.setText(translatedText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSwitchButton() {
        inputTextZone.clear();
        outputTextZone.clear();
        if (englishLabel.getLayoutX() == 71.0) {
            englishLabel.setLayoutX(434.0);
            vietnameseLabel.setLayoutX(71.0);
            ok = true;
            return;
        }
        if(vietnameseLabel.getLayoutX() == 71.0) {
            englishLabel.setLayoutX(71.0);
            vietnameseLabel.setLayoutX(434.0);
        }
    }

    @FXML
    private void handleSoundButtonInput() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(inputTextZone.getText());
        } else throw new IllegalStateException("Can't find the voice!");
    }

    @FXML
    private void handleSoundButtonOutput() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(outputTextZone.getText());
        } else throw new IllegalStateException("Can't find the voice!");
    }
}
