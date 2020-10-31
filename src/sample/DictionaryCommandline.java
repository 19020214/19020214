package sample;

import java.io.IOException;
import java.util.ArrayList;
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
        System.out.println(DictionaryManagement.dictionaryLookup("hello"));
//        DictionaryManagement.dictionaryExportToFile();
        System.out.println(dictionarySearcher("h"));
    }
    public static void main(String[] args) throws IOException {
        // DictionaryManagement dm = new DictionaryManagement();
        // dm.insertFromCommandline();
        DictionaryCommandline dc = new DictionaryCommandline();
//        dc.dictionaryBasic();
        dc.dictionaryAdvanced();
    }
    public ArrayList<String> dictionarySearcher(String searchWord) {
//        String Query;
//        Scanner scanner = new Scanner(System.in);
//        Query = scanner.nextLine();
        ArrayList arrayList = new ArrayList<String>();
        System.out.println(searchWord);
        for (int i = 0; i < Dictionary.words.size(); i++) {
            String word =  Dictionary.words.get(i).getWord_target();
            if ((word.startsWith(searchWord))) {
                arrayList.add(word);
            }
        }
        return arrayList;
    }
}