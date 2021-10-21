package com.example.dictionary_ver3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictionaryController implements Initializable {

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            Logger.getLogger(DictionaryController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void home(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    }

    public void online(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("offline.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void searcher(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("searcher.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void add(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("add.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("edit.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void about(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("about.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
