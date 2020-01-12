package edu.szymkowski.szymczak;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Ships extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        Parent root =  FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}