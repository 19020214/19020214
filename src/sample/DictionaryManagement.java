package sample;

import java.io.*;
import java.nio.file.Paths;
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
        Scanner scanner = new Scanner(Paths.get("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary\\src\\dictionaries.txt"), "UTF-8");
        File dictionaries = new File("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary\\src\\dictionaries.txt");
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
    public static void dictionaryLookup() {
        String word_Lookup;
        Scanner scanner = new Scanner(System.in);
        word_Lookup = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWord_target().equals(word_Lookup) || Dictionary.words.get(i).getWord_explain().equals("\t"+word_Lookup)) {
                check = true;
            }
        }
        if (check) System.out.println("Found");
        else System.out.println("Not found");
    }
    public static void dictionaryExportToFile() throws IOException {
        File dictionaries = new File("C:\\Users\\LENOVO\\IdeaProjects\\Dictionary\\src\\dictionaries.txt");
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
}