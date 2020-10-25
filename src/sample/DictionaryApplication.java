package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DictionaryApplication extends Application {

    @Override
    /**
     * runApplication();
     */
    public void start (Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass( ).getResource("sample.fxml"));
            Scene sence = new Scene(root);
            primaryStage.setScene(sence);
            primaryStage.setTitle("DICTIONARY");
            primaryStage.show( );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dc = new DictionaryCommandline();
        DictionaryManagement.insertFromFile();
        launch(args);

    }
}