package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a word with its definition
public class Word implements Writable {
    String word;
    String definition;

    // MODIFIES: this.
    // EFFECTS: Initializes class with null word and null definition
    public Word() {
        this.word = null;
        this.definition = null;
    }

    // For test Purposes
    public Word(String name, String def) {
        this.word = name;
        this.definition = def;
    }

    // EFFECTS: returns word variable word
    public String getWord() {
        return word;
    }

    // MODIFIES: this.
    // EFFECTS: sets word variable word
    public void setWord(String word) {
        this.word = word;
    }

    // EFFECTS: returns word variable definition
    public String getDefinition() {
        return definition;
    }

    // MODIFIES: this
    // EFFECTS: Sets the variable definitionr
    public void setDefinition(String def) {
        this.definition = def;
    }

    // EFFECTS: Returns the class data in a JSONObject.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("word", word);
        json.put("definition", definition);
        return json;
    }
}
