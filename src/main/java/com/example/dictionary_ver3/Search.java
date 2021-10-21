package com.example.dictionary_ver3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Search {

    @FXML
    private ListView searchResult;
    @FXML
    private TextField searchWord;
    @FXML
    private TextArea vieResult;

    public void initialize() {
        vieResult.setEditable(false);
    }

    public void searchResult(KeyEvent event) throws IOException {
        String search = searchWord.getText();
        List<String> searchList = DictionaryCommandline.dictionarySearcher(search);
        Collections.sort(searchList);
        ObservableList<String> res = FXCollections.observableArrayList();
        for (String word : searchList) {
            res.add(word);
        }
        searchResult.setItems(res);
        searchList.clear();
        searchResult.getSelectionModel().selectIndices(0);
    }

    public void wordSearching(MouseEvent event){
        Object temp = searchResult.getSelectionModel().getSelectedItem();
        if (temp == null) {
            //System.out.println("null check!");
        } else {
            String tempTxt = DictionaryManagement.dictionaryLookup((String) temp);
            vieResult.setText(DictionaryManagement.makeTrueForm(tempTxt));
        }
    }


    public void speakerShow(MouseEvent event) {
        //System.out.println("Speech");
        DictionaryManagement.wordSpell(searchWord.getText());
    }

    public void spellWord(ActionEvent event) {
        //System.out.println("Test Speech");
        Object temp = searchResult.getSelectionModel().getSelectedItem();
        if (temp == null) {
            System.out.println("null check:");
        } else {
            DictionaryManagement.wordSpell(((String) temp));
        }
    }
}
