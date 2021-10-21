package com.example.dictionary_ver3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Edit {

    @FXML
    private TextField editSearch;
    @FXML
    private ListView editList;
    @FXML
    private TextField deleteLine;
    @FXML
    private TextField editWord;
    @FXML
    private TextArea editInfo;
    @FXML
    private GridPane gridEdit;

    public void initialize() {
        gridEdit.setDisable(true);
    }

    String searchWord;

    public void search(KeyEvent event) {
        String editWord = editSearch.getText();
        List<String> searchList = DictionaryCommandline.dictionarySearcher(editWord);
        Collections.sort(searchList);
        ObservableList<String> res = FXCollections.observableArrayList();
        for (String word : searchList) {
            res.add(word);
        }
        editList.setItems(res);
        searchList.clear();
        editList.getSelectionModel().selectIndices(0);
    }

    public void editMenu(MouseEvent event) throws IOException {
        Object temp = editList.getSelectionModel().getSelectedItem();
        searchWord = (String) temp;
        if (temp == null) {
            //System.out.println("null check:");
        } else {

            Alert menu = new Alert(AlertType.NONE);
            menu.setTitle("Select:");
            menu.setHeaderText("DELETE or EDIT ?");

            ButtonType delete = new ButtonType("Delete");
            ButtonType edit = new ButtonType("Edit");
            ButtonType cancel = new ButtonType("Cancel");

            menu.getButtonTypes().addAll(delete, edit, cancel);

            Optional<ButtonType> option = menu.showAndWait();
            if (option.isPresent()) {
                if (option.get() == delete) {
                    //Delete
                    Object tempObj = editList.getSelectionModel().getSelectedItem();
                    DictionaryManagement.deleteWord((String) tempObj);
                    Alert succes = new Alert(AlertType.INFORMATION);
                    succes.setTitle("Delete Successful!");
                    succes.setHeaderText("Deleted your new WORD!");
                    succes.show();
                } else if (option.get() == edit) {
                    //Edit
                    editList.setDisable(true);
                    editSearch.setDisable(true);
                    String targetText = (String) temp;
                    String explainText = DictionaryManagement.makeTrueForm(DictionaryManagement.dictionaryLookup((String) temp));
                    gridEdit.setDisable(false);
                    editWord.setText(targetText);
                    editInfo.setText(explainText);
                }
            } else {
                //System.out.println("Cancel!");
            }
        }
    }

    public void editDone(MouseEvent event) {
        editList.setDisable(false);
        editSearch.setDisable(false);
        deleteLine.setText(editInfo.getText());
        String wordInfo = deleteLine.getText();
        //System.out.println(wordInfo);
        String wordTarget = editWord.getText();

        if (DictionaryCommandline.searchWord(wordTarget) && !wordTarget.equals(searchWord)) {
            // Fail alert.
            Alert fail = new Alert(AlertType.ERROR);
            fail.setTitle("Edit Fail!");
            fail.setHeaderText("Your new Word already exists");
            fail.show();
        } else {
            Word newOne = new Word(wordTarget, wordInfo);
            DictionaryManagement.dictionaryEdit(searchWord, newOne);
            gridEdit.setDisable(true);

            Alert done = new Alert(AlertType.INFORMATION);
            done.setTitle("Edit Successful!");
            done.setHeaderText("Edited your WORD!");
            done.show();
        }
    }

    public void cancelEdit(MouseEvent event) {
        String explainText = DictionaryManagement.makeTrueForm(DictionaryManagement.dictionaryLookup(searchWord));
        editWord.setText(explainText);
        editInfo.setText(explainText);
        editList.setDisable(false);
        editSearch.setDisable(false);
        gridEdit.setDisable(true);
    }
}
