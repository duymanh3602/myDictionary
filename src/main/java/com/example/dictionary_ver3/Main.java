package com.example.dictionary_ver3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        primaryStage.setTitle("myDictionary");
        primaryStage.setScene(new Scene(root, 1080, 540));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement.insertFromFile();
        launch(args);
        DictionaryManagement.dictionaryExportToFile();

    }
}

