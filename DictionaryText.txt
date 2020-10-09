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
    public static void main(String[] args) {
        // DictionaryManagement dm = new DictionaryManagement();
        // dm.insertFromCommandline();
        DictionaryCommandline dc = new DictionaryCommandline();
        dc.dictionaryBasic();

    }
}





