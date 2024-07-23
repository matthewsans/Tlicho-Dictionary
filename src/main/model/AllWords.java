package model;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

// Represents all  words known in dictionary
public class AllWords extends WordSet {

    //MODIFIES: this
    //EFFECTS: Initialize AllWords with all known current words
    public AllWords() {
        super();
    }

    // EFFECTS: Produces a word of the day by using today's date mod the length of dic. list.
    public String wordOfDay() {
        int day = Integer.parseInt(((LocalDate.now()).toString()).substring(8));
        int index = day % totalList.size();
        Word wordOfDay = getIndexWord(index);
        return (wordOfDay.getWord() + ": " + wordOfDay.getDefinition());
    }

    //EFFECTS; Returns class data (AllWords) into a JSONObject
    @Override
    public JSONObject toJson() {
        return super.toJson("allwords");
    }
}
