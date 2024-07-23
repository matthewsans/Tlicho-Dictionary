package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

// Represents a general dictionary
public class WordSet implements Writable {
    protected List<Word> totalList;
    protected Event event;

    //MODIFIES: this
    //EFFECTS: Initialize AllWords with all known current words
    public WordSet() {
        totalList = new ArrayList<Word>();
    }

    //MODIFIES: this
    //EFFECTS: Runs through InitialWordSet.csv line by line reading it.
    // to totalList.
    public void initialize() throws IOException {
//        File directory = new File("./data/InitialWordSet.csv");
//        BufferedReader br = new BufferedReader(new FileReader(directory));
//        String line = br.readLine();
//        while ((line = br.readLine()) != null) {
//            // Break line into entries using comma
//            String[] items = line.split(",");
//            // If there are too many entries, throw a dummy exception, if
//            // there are too few, the same exception will be thrown later
//            if (items.length > 2) {
//                throw new ArrayIndexOutOfBoundsException();
//            }
//            // Convert data to word record
//            Word word = new Word();
//            word.setWord(items[0]);
//            word.setDefinition(items[1]);
//            totalList.add(word);
//        }
        Files.lines(Paths.get("./data/InitialWordSet.csv")).forEach(this::addInitialWords);

    }

    //MODIFIES: this
    //EFFECTS: Helper function for initialize(), contains the parameters to parse InitialWordSet.csv.
    //         adds word and definition to a new Word, then adds that word to totalList
    public void addInitialWords(String line) {
        String[] items = line.split(",");
        Word word = new Word();
        word.setWord(items[0]);
        try {
            word.setDefinition(items[1]);
            totalList.add(word);

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(items[0]);
        }
    }

    //MODIFIES: this
    //EFFECTS: Returns the variable totalList
    public List getTotalList() {
        return totalList;
    }

    //MODIFIES: this
    //EFFECTS: Appends a Word to the end of totalList
    public void addWord(Word word) {
        EventLog.getInstance().logEvent(new Event("Added a word"));

        totalList.add(word);
    }

    //MODIFIES: this
    //EFFECTS: Removes the first instance of the given string from totalList
    public Boolean removeWord(String word) {
        Word remove = null;
        for (Word w : totalList) {
            String testWord = w.getWord();
            if (testWord.equals(word)) {
                remove = w;
                break;
            }
        }
        EventLog.getInstance().logEvent(new Event("Removed a word"));

        return totalList.remove(remove);
    }

    //old method, left in case we need to reuse it.
    /*
    //EFFECTS: Returns
    public String findDefinition(String word) {
        for (Word w: totalList) {
            if (w.getWord() == word) {
                return w.getDefinition();
            }
        }
        return "Word is not in our dictionary";
    }
     */

    // REQUIRES: (Index - 1) <= totalList.size(), List must not be empty
    // EFFECTS: returns the index in list with given int
    public Word getIndexWord(int index) {
        return totalList.get(index);
    }

    // REQUIRES: totalList is  not empty
    // EFFECTS: Returns a list of words from totalList that either start with the given input string, or
    //         contains the given input string in its definition.
    public List lookupWord(String word) {
        List matches = new ArrayList<Word>();
        for (Word w : totalList) {
            if ((w.getWord()).startsWith(word) || (w.getDefinition()).contains(word)) {
                matches.add(w);
            }
        }

        EventLog.getInstance().logEvent(new Event("Searched a term"));
        return matches;
    }

    // EFFECTS: Returns the class data into a JSONObject
    public JSONObject toJson() {
        return toJson("wordset");
    }

    // MODIFIES: this.
    // EFFECTS: Returns the class data into a JSONObject, Used for subclasses to insert their own class.
    public JSONObject toJson(String subClass) {
        JSONObject json = new JSONObject();
        json.put(subClass, wordsToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: returns totalList in this WordSet as a JSON array
    public JSONArray wordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Word t : totalList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
