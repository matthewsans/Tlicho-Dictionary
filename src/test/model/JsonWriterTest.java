package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Test for JsonWriter
public class JsonWriterTest {
    private AllWords allWords;
    private Favourites favourites;
    Word biggie;
    Word killer;
    Word grandMaster;
    Word wutang;

    @BeforeEach
    public void runBefore() {
        allWords = new AllWords();
        favourites = new Favourites();
        biggie = new Word("biggie", "smalls");
        killer = new Word("killer", "mike");
        grandMaster = new Word("grandmaster", "flash");
        wutang = new Word("wutang", "forever");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my/illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLists() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLists.json");
            writer.open();
            writer.write(allWords, favourites);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLists.json");
            allWords = (AllWords) reader.read("allwords");
            favourites = (Favourites) reader.read("favourites");
            assertEquals(0, (allWords.getTotalList()).size());
            assertEquals(0, (favourites.getTotalList()).size());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLists() {
        try {
            addWords();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLists.json");
            writer.open();
            writer.write(allWords, favourites);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLists.json");
            allWords = (AllWords) reader.read("allwords");
            favourites = (Favourites) reader.read("favourites");
            assertEquals(2, (allWords.getTotalList()).size());
            assertEquals(2, (favourites.getTotalList()).size());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void addWords() {
        allWords.addWord(killer);
        allWords.addWord(grandMaster);
        favourites.addWord(wutang);
        favourites.addWord(biggie);
    }


}
