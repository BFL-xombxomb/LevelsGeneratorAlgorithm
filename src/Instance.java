import java.util.ArrayList;

public class Instance {

    private String startWord;
    private ArrayList<String> chain;

    public Instance() {

        this.chain = new ArrayList<>();
    }

    public Instance(String startWord) {
        this.startWord = startWord;
        this.chain = new ArrayList<>();
    }

    public Instance(String startWord, ArrayList<String> chain) {
        this.startWord = startWord;
        this.chain = chain;
    }

    public String getStartWord() {
        return startWord;
    }

    public void setStartWord(String startWord) {
        this.startWord = startWord;
    }

    public ArrayList<String> getChain() {
        return chain;
    }

    public void setChain(ArrayList chain) {
        this.chain = chain;
    }
}
