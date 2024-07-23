package persistence;

import model.AllWords;
import model.Favourites;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

// Writes Json representation of AllWords and Favourites
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of WordSet to file
    public void write(AllWords allWords, Favourites favourites) {
        JSONObject json = new JSONObject();
        JSONObject jsonAllWords = allWords.toJson();
        JSONObject jsonFavourites = favourites.toJson();
        JSONObject mergedObj = new JSONObject();

        Iterator i1 = jsonAllWords.keys();
        Iterator i2 = jsonFavourites.keys();
        String tmpKey;
        while (i1.hasNext()) {
            tmpKey = (String) i1.next();
            mergedObj.put(tmpKey, jsonAllWords.get(tmpKey));
        }
        while (i2.hasNext()) {
            tmpKey = (String) i2.next();
            mergedObj.put(tmpKey, jsonFavourites.get(tmpKey));
        }
        saveToFile(mergedObj.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: data/SavedDictionary.json
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
