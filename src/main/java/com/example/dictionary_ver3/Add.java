package com.example.dictionary_ver3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Add {
    @FXML
    private TextField searchWord;
    @FXML
    private TextField consoleLog;
    @FXML
    private GridPane gridAdd;
    @FXML
    private Button addButton;
    @FXML
    private TextField makeFormTemp;
    @FXML
    private TextField newPron;
    @FXML
    private TextField newPOS;
    @FXML
    private TextArea newMean;

    public void initialize() {
        gridAdd.setDisable(true);
        addButton.setDisable(true);
        consoleLog.setEditable(false);
        consoleLog.setStyle("-fx-text-fill: black");
    }

    public void search(KeyEvent event) {
        String inputWord = searchWord.getText();
        boolean check = DictionaryCommandline.searchWord(inputWord.trim());
        if (inputWord.length() == 0) {
            searchWord.setStyle("-fx-border-color: red;" +
                    "-fx-border-width: 2px 2px 2px 2px;");
            consoleLog.setText("Không thể thêm ký tự trống");
            addButton.setDisable(true);
        } else if (!check) {
            searchWord.setStyle("-fx-border-color: #00FF00;" +
                    "-fx-border-width: 2px 2px 2px 2px;");
            consoleLog.setText("Có thể thêm từ này");
            addButton.setDisable(false);
        } else {
            searchWord.setStyle("-fx-border-color: red;" +
                    "-fx-border-width: 2px 2px 2px 2px;");
            consoleLog.setText("Từ này đã tồn tại!");
            addButton.setDisable(true);
        }
    }

    //Unlock girdPane
    public void gridPaneAble(MouseEvent event) {
            gridAdd.setDisable(false);
    }

    //Done Button
    public void addNewWord(MouseEvent event) {

        Word newOne = new Word();
        newOne.setWordTarget((String) searchWord.getText());
        makeFormTemp.setText(newMean.getText());
        String explain = "/" + newPron.getText().trim() + "/ *  " +newPOS.getText().trim()+" - " + makeFormTemp.getText().trim();
        newOne.setWordExplain(explain);
        //System.out.println(explain);
        DictionaryManagement.addNewWord(newOne);
        //DictionaryManagement.dictionaryExportToFile();

        // Alert Succes!
        Alert succes = new Alert(AlertType.INFORMATION);
        succes.setTitle("Add Successful!");
        succes.setHeaderText("Added your new WORD!");
        succes.show();
    }

}

