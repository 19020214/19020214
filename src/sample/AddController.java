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

public class AddController {
    @FXML
    public TextField tfAddE;
    @FXML
    public TextField tfAddV;
    @FXML
    public Button Submit;
    @FXML
    public Button Back;
    private ActionEvent e;

    public void clickAddWord(ActionEvent e) throws IOException {
        String wordE = tfAddE.getText();
        String wordV = tfAddV.getText();
        if (DictionaryManagement.dictionaryLookup(wordE) == 0){
            DictionaryManagement.insertWord(wordE, wordV);
            Word newWord = new Word(wordE, wordV);
            Dictionary.words.add(newWord);
        }
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
