package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// reads data from SavedDictionary.json
public class JsonReaderTest {
    JsonReader reader;
    @BeforeEach
    public void setup() {

    }

    @Test
    void testReaderNonExistentFile() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllWords allWords =(AllWords) reader.read("allwords");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLists() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLists.json");
        try {
            AllWords allWords =(AllWords) reader.read("allwords");
            Favourites favourites =(Favourites) reader.read("favourites");
            assertEquals(0, (allWords.getTotalList()).size());
            assertEquals(0, (favourites.getTotalList()).size());
        } catch (IOException e) {
            fail("file could not be read");
        }
    }

    @Test
    void testReaderGeneralLists() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLists.json");
        try {
            AllWords allWords =(AllWords) reader.read("allwords");
            Favourites favourites =(Favourites) reader.read("favourites");
            assertEquals(2, (allWords.getTotalList()).size());
            assertEquals(2, (favourites.getTotalList()).size());
        } catch (IOException e) {
            fail("file could not be read");
        }
    }
}
