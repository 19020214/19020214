package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DictionaryApplication extends Application {

    @Override
    /**
     * runApplication();
     */
    public void start (Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass( ).getResource("sampleA.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Dictionary");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement.insertFromFile();
        launch(args);
    }
}