package sample;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.sun.javafx.logging.PlatformLogger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;

import javax.print.DocFlavor;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements Initializable {
//    @FXML
//    public Button Search;
    @FXML
    public Button Add;
    @FXML
    public Button Fix;
    @FXML
    public Button Delete;
    @FXML
    public TextField tfEnterWord;
    @FXML
    public Button Voice;
    @FXML
    public ImageView imgv;
    @FXML
    public ImageView imgv1;
    @FXML
    public ImageView imgv2;
    @FXML
    public ImageView imgv3;
    @FXML
    public ImageView imgv4;
//    @FXML
//    public TextField tfAddE;
//    @FXML
//    public TextField tfAddV;
    @FXML
    public TextArea Emeaning;
    public ObservableList<String> ele = FXCollections.observableArrayList();
    @FXML ListView<String> lv = new ListView<>();
    DictionaryCommandline dc = new DictionaryCommandline();
    @FXML
    private ActionEvent e;
    private static Synthesizer synthesizer;


    public void setSearch(){
        imgv.setVisible(false);
        Voice.setVisible(false);
        //tfEnterWord = new TextField();
        ele.clear();
        Emeaning.clear();
        String temp = tfEnterWord.getText();
        ArrayList<String> list = new ArrayList<>();
        list = dc.dictionarySearcher(temp);
        for (int i = 0;i< list.size();i++){
            ele.add(list.get(i));
        }
        lv.setItems(ele);
    }

//    public void setBtSearch()throws IOException {
//        String temp = tfEnterWord.getText();
//        if (temp.equals("")) Emeaning.clear();
//        if (DictionaryManagement.dictionaryLookup(temp) != 0){
//            String explainWord = Dictionary.words.get(DictionaryManagement.dictionaryLookup(temp)-1).getWord_explain().trim();
//            Emeaning.setText(explainWord);
//        }
//    }

    public void clickSearch() {
        Voice.setVisible(true);
        imgv.setVisible(true);
        Voice.setPickOnBounds(true);
        imgv.setImage(new Image(new File("speaker.png").toURI().toString()));
        String temp = lv.getSelectionModel().getSelectedItem();
//        String explainWord = Dictionary.words.get(DictionaryManagement.dictionaryLookup(temp)-1).getWord_explain().trim();
        tfEnterWord.setText(temp);
        if (temp.equals("")) Emeaning.clear();
        if (DictionaryManagement.dictionaryLookup(temp) != 0){
            String explainWord = Dictionary.words.get(DictionaryManagement.dictionaryLookup(temp)-1).getWord_explain().trim();
            Emeaning.setText(explainWord);
        }
    }

    public void changeSceneAdd(javafx.event.ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleAdd.fxml"));
        Parent addParent = loader.load();
        Scene scene = new Scene(addParent);
        stage.setScene(scene);
    }

    public void changeSceneDelete(javafx.event.ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleDelete.fxml"));
        Parent deleteParent = loader.load();
        Scene scene = new Scene(deleteParent);
        stage.setScene(scene);
    }

    public void changeSceneFix(javafx.event.ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sampleFix.fxml"));
        Parent fixParent = loader.load();
        Scene scene = new Scene(fixParent);
        stage.setScene(scene);
    }
    public void clickVoice() {
        String word = tfEnterWord.getText();
        if (DictionaryManagement.dictionaryLookup(word) != 0){
            try {
                System.setProperty(
                        "freetts.voices",
                        "com.sun.speech.freetts.en.us"
                                + ".cmu_us_kal.KevinVoiceDirectory");
                Central.registerEngineCentral(
                        "com.sun.speech.freetts"
                                + ".jsapi.FreeTTSEngineCentral");
                synthesizer = Central.createSynthesizer(
                        new SynthesizerModeDesc(Locale.US));
                synthesizer.allocate();

                synthesizer.resume();

            } catch (EngineException | AudioException e) {
                e.printStackTrace();
            }

            Voice.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        synthesizer.speakPlainText(tfEnterWord.getText(), null);
//                    synthesizer.waitEngineState(
//                            Synthesizer.QUEUE_EMPTY);
//
//                    // Deallocate the Synthesizer.
//                    synthesizer.deallocate();
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("listing-option.png");
        Image image = new Image(file.toURI().toString());
        imgv1.setImage(image);
        File file1 = new File("loupe.png");
        Image image1 = new Image(file1.toURI().toString());
        imgv2.setImage(image1);
        File file2 = new File("clock.png");
        Image image2 = new Image(file2.toURI().toString());
        imgv3.setImage(image2);
        File file3 = new File("star.png");
        Image image3 = new Image(file3.toURI().toString());
        imgv4.setImage(image3);
    }

}