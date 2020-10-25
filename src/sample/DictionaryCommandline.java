package sample;

import java.io.IOException;
import java.util.Scanner;

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