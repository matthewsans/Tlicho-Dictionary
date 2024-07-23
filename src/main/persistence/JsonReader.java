package persistence;

import model.AllWords;
import model.Favourites;
import model.Word;
import model.WordSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reads data from SavedDictionary.json
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WordSet read(String type) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWordSet(jsonObject, type);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // REQUIRES: JSONObject in SavedDictionary.json to have proper formatting with "allwords" and "favourites"
    // MODIFIES: WordSet
    // EFFECTS: parses WordSet from JSON object and returns it
    public WordSet parseWordSet(JSONObject jsonObject, String type) {
        WordSet wordSet;
        if (type.equals("allwords")) {
            wordSet = new AllWords();
        } else if (type.equals("favourites")) {
            wordSet = new Favourites();
        } else {
            wordSet = new WordSet();
        }
        JSONArray jsonArray = jsonObject.getJSONArray(type);
        for (Object json : jsonArray) {
            JSONObject nextWord = (JSONObject) json;
            addWord(wordSet, nextWord);
        }
        return wordSet;
    }

    // MODIFIES: wordSet
    // EFFECTS: parses Word from JSON object and adds it to wordSet
    public void addWord(WordSet wordset, JSONObject jsonObject) {
        String word = jsonObject.getString("word");
        String definition = jsonObject.getString("definition");
        Word newWord = new Word(word, definition);
        wordset.addWord(newWord);
    }
}
