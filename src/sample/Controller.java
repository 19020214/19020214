package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;

public class Controller {
    @FXML
    public TextField word;
    public Button Search;
    public TextArea list;
    public TextField word_explane;
    public ImageView brandingImageView;
    public DictionaryCommandline dc = new DictionaryCommandline();

    public ObservableList<String> ele = FXCollections.observableArrayList();
    @FXML ListView<String> stringListView = new ListView<>(  );
    public void initbackground (URL url, ResourceBundle resourceBundle){
        File imageurl = new File("your-logo.png");
        Image image = new Image(imageurl.toURI().toString());
        brandingImageView.setImage(image);
    }
    public void init () throws IOException {
        ele.clear();
        String temp = word.getText();
        ArrayList<String> list = new ArrayList<>();
        list =  dc.dictionarySearcher(temp);
        for (int i = 0; i < list.size(); i++) {
            ele.add(list.get(i));
        }
        stringListView.setItems(ele);
    }
    public void setSearch() throws IOException{
        Search.setOnMouseClicked(event -> {
            String temp = word.getText();
            if(DictionaryManagement.dictionaryLookup(temp) != 0) {
                String explain =  Dictionary.words.get(DictionaryManagement.dictionaryLookup(temp) - 1)
                        .getWord_explain() ;
                word_explane.setText(explain);
            }
        });
    }
}