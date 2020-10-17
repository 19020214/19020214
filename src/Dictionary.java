import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Word {
    private String word_target;
    private String word_explain;

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }
    public Word(){
        this.word_explain = "";
        this.word_target = "";
    }
    public Word(String _word_target , String _word_explain){
        this.word_target = _word_target;
        this.word_explain = _word_explain;
    }
}

class Dictionary {
    public static ArrayList<Word> words = new ArrayList<Word>();
}
class DictionaryManagement {
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
class DictionaryCommandline {
    public void showAllWords(){
        //   Dictionary dic = new Dictionary();
        System.out.println("No    | English      | Vietnamese ");
        for(int i = 0; i < Dictionary.words.size(); i++){
            System.out.println(i + 1 + "     | " + Dictionary.words.get(i).getWord_target() + "        | " + Dictionary.words.get(i).getWord_explain());
        }
    }
    public void dictionaryBasic(){
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    public void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile();
        showAllWords();
//        DictionaryManagement.dictionaryLookup();
//        DictionaryManagement.dictionaryExportToFile();
        dictionarySearcher();
    }
    public static void main(String[] args) throws IOException {
        // DictionaryManagement dm = new DictionaryManagement();
        // dm.insertFromCommandline();
        DictionaryCommandline dc = new DictionaryCommandline();
//        dc.dictionaryBasic();
        dc.dictionaryAdvanced();
    }
    public void dictionarySearcher() {
        String Query;
        Scanner scanner = new Scanner(System.in);
        Query = scanner.nextLine();
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if ((Dictionary.words.get(i).getWord_target().startsWith(Query)) && (Dictionary.words.get(i).getWord_target().contains(Query))) {
                System.out.println(Dictionary.words.get(i).getWord_target());
            }
        }
    }
}





