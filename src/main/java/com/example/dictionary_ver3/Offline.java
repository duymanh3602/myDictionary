package com.example.dictionary_ver3;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;


// Đặt nhầm tên ->Online
public class Offline {
    @FXML
    private TextArea inputWord;
    @FXML
    private TextArea resultTxt;
    @FXML
    private TextField modeText;
    @FXML
    private ToggleButton modeButton;

    public static String modeTrans;
    public void initialize() {
        modeTrans = "en-vi";
        inputWord.setWrapText(true);
        modeText.setEditable(false);
        resultTxt.setWrapText(true);
        resultTxt.setEditable(false);
        modeText.setText("ENG to VIE");
    }

    public void setModeTrans(MouseEvent event) {
        if (modeButton.isSelected()) {
            modeTrans = "vi-en";
            modeText.setText("VIE to ENG");
            inputWord.setText("");
            resultTxt.setText("");
        } else {
            modeTrans = "en-vi";
            modeText.setText("ENG to VIE");
            inputWord.setText("");
            resultTxt.setText("");
        }
    }

    public void onlineSearch(MouseEvent event) throws Exception {
        String translateTxt = inputWord.getText().trim();
        if (translateTxt != null && translateTxt.length() >0) {
            String res = DictionaryManagement.getOnline(translateTxt,modeTrans);
            resultTxt.setText(DictionaryManagement.getTranslate(res));
        } else {
            System.out.println("false");
        }
    }

    public void voice(MouseEvent event) throws Exception {
        String temp = inputWord.getText().trim();
        if (temp.length() > 0 && temp != null) {
            if (modeTrans.equals("en-vi")) {
                VoiceAPI.getVoiceEng(temp);
                VoiceAPI.play("eng_voice.wav");
            } else {
                VoiceAPI.getVoiceVie(temp);
                VoiceAPI.play("vie_voice.wav");
            }
        }
    }
    public void voice2(MouseEvent event) throws Exception {
        String temp = resultTxt.getText().trim();
        if (temp.length() > 0 && temp != null) {
            if (modeTrans.equals("en-vi")) {
                VoiceAPI.getVoiceVie(temp);
                VoiceAPI.play("vie_voice.wav");
            } else {
                VoiceAPI.getVoiceVie(temp);
                VoiceAPI.play("eng_voice.wav");
            }
        }
    }

}
