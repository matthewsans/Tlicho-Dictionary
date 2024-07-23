package model;

import org.json.JSONObject;

import java.util.List;

// Represents list of favourite words in dictionary
public class Favourites extends WordSet {

    //MODIFIES: this
    //EFFECTS; Initialize AllWords with all known current words
    public Favourites() {
        super();
    }

    //REQUIRES: Word not currently in favourites list.
    //MODIFIES: this.
    //EFFECTS: Adds word to favourites list if it appears in main list, returns Boolean upon successful add
    public boolean addWord(List<Word> list, String word) {
        for (Word w : list) {
            String testWord = w.getWord();
            if (testWord.equals(word)) {
                super.addWord(w);
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        return super.toJson("favourites");
    }
}
