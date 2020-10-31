package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.ArrayList;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < total; i++) {
            String a, b;
            a = scanner.nextLine();
            b = scanner.nextLine();
            Word word = new Word(a,b);
            Dictionary.words.add(word);
        }
    }
    public static void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary2\\dictionaries.txt"), "UTF-8");
        File dictionaries = new File("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary2\\dictionaries.txt");
        if(dictionaries.exists()){
            while (scanner.hasNextLine()) {
                String a = scanner.next();
                String b = scanner.nextLine();
                Word word = new Word(a, b);
                Dictionary.words.add(word);
            }
            scanner.close();
        }
        else System.out.println("Error");
    }
    public static int dictionaryLookup(String word_Lookup) {
//        Scanner scanner = new Scanner(System.in);
//        word_Lookup = scanner.nextLine();
        int i;
        boolean check = false;
        for (i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWord_target().equals(word_Lookup) || Dictionary.words.get(i).getWord_explain().equals("\t"+word_Lookup)) {
                check = true;
                break;
            }
        }
//        if (check) System.out.println("Found");
//        else System.out.println("Not found");
        if(check){
            return i + 1;
        }
        else return 0;
    }

    public static void dictionaryExportToFile() throws IOException {
        File dictionaries = new File("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary2\\dictionaries.txt");
        OutputStream outputStream = new FileOutputStream(dictionaries);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for (int i = 0; i < Dictionary.words.size(); i++) {
            outputStreamWriter.write(Dictionary.words.get(i).getWord_target());
            outputStreamWriter.write("\t");
            outputStreamWriter.write(Dictionary.words.get(i).getWord_explain());
            outputStreamWriter.write("\n");
        }
        outputStreamWriter.flush();
    }
    public static void insertWord(String target_word, String explain_word) throws IOException{
        System.out.println(target_word + " " + explain_word);
        Word word = new Word(target_word, explain_word);
//        for (int i = 0 ; i < Dictionary.words.size();i++){
////            String str = Dictionary.words.get(i).getWord_target()  + Dictionary.words.get(i).getWord_explain() + "\n";
//        }
        Writer output = new BufferedWriter(new FileWriter("dictionaries.txt", true));
        output.append(target_word + "\t" + explain_word + "\n");
        output.close();
    }
    public static void fixWord(String wordToRepair, String newTarget, String newExplain ) throws IOException{
        int count = dictionaryLookup(wordToRepair);
        FileWriter fwrw = new FileWriter("dictionaries.txt");
        for (int i = 0 ; i < Dictionary.words.size();i++){
            String str = Dictionary.words.get(i).getWord_target() + "\t" + Dictionary.words.get(i).getWord_explain() + "\n";
            if (i != count - 1) fwrw.write(str);
            else fwrw.write(newTarget  +"\t"+ newExplain + "\n");
        }
        fwrw.close();
    }
    public static void deleteWord(String wordtoDelete) throws IOException{
        int count = dictionaryLookup(wordtoDelete);
        FileWriter fwdw = new FileWriter("dictionaries.txt");
        for (int i = 0 ; i < Dictionary.words.size() ; i++){
            String str = Dictionary.words.get(i).getWord_target() + Dictionary.words.get(i).getWord_explain() + "\n";
            if(i != count - 1) {
                fwdw.write(str);
            }
        }
        fwdw.close();
    }

}