package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FixController {
    @FXML
    public TextField tfAddE1;
    @FXML
    public TextField tfAddE2;
    @FXML
    public TextField tfAddV;
    @FXML
    public Button Submit;
    @FXML
    public Button Back;

    public void clickFixWord(ActionEvent e) throws IOException {
        String wordE1 = tfAddE1.getText();
        String wordE2 = tfAddE2.getText();
        String wordV = tfAddV.getText();
        DictionaryManagement.fixWord(wordE1,wordE2,wordV);
        Dictionary.words.clear();
        DictionaryManagement.insertFromFile();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleA.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }
    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleA.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }
}
