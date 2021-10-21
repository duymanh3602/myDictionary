package com.example.dictionary_ver3;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class About {
    @FXML
    private TextField count;

    public void initialize() {
        count.setText("Số lượng từ hiện có trong từ điển: " + DictionaryCommandline.listOfWords.size());
    }
}
