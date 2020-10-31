package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


import java.io.IOException;

public class DeleteController {
    @FXML
    public TextField tfAddE;
    @FXML
    public Button Submit;
    @FXML
    public Button Back;
    private ActionEvent e;

    public void clickDeleteWord(ActionEvent e) throws IOException {
        String wordE = tfAddE.getText();
        if (DictionaryManagement.dictionaryLookup(wordE) != 0){
            DictionaryManagement.deleteWord(wordE);
            //Dictionary.words.remove(wordE);
            Dictionary.words.clear();
            DictionaryManagement.insertFromFile();
        }
        System.out.println(Dictionary.words);

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
