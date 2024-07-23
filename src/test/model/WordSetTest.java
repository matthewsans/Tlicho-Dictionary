package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//test class for WordSet
public class WordSetTest {
    WordSet wordSet;
    Word before;
    Word each;
    JsonReader reader;

    @BeforeEach
    public void setUp() {
        wordSet = new WordSet();
        before = new Word("before", "to be first");
        each = new Word("each", "to be last");
        reader = new JsonReader("./data/testReaderGeneralLists.json");
    }

    @Test
    //MODIFIES: this
    //EFFECTS: Returns the variable totalList
    public void getTotalListTest() {
        assertTrue((wordSet.getTotalList()).isEmpty());
        wordSet.addWord(before);
        assertEquals(before, wordSet.getIndexWord(0));
        wordSet.addWord(each);
        assertEquals(each, wordSet.getIndexWord(1));
    }

    @Test
    //MODIFIES: this
    //EFFECTS; Appends a Word to the end of totalList
    public void addWordTest() {
        assertTrue((wordSet.getTotalList()).isEmpty());
        wordSet.addWord(before);
        assertEquals(before, wordSet.getIndexWord(0));

    }

    @Test
    public void removeWordTest() {
        wordSet.addWord(before);
        assertTrue(wordSet.removeWord("before"));
        assertEquals(0, (wordSet.getTotalList()).size());
    }

    @Test
    public void removeWordInvalidTest() {
        wordSet.addWord(before);
        assertFalse(wordSet.removeWord("totooto"));
        assertEquals(1, (wordSet.getTotalList()).size());
    }

    /*
    @Test
    public void findDefinitionTest() {
        wordSet.addWord(before);
        String def = wordSet.findDefinition("before");
        assertEquals(before.getDefinition(), def);
    }

    @Test
    public void findDefinitionNotFoundTest() {
        wordSet.addWord(before);
        String def = wordSet.findDefinition("qwerty");
        assertEquals("Word is not in our dictionary", def);
    }
     */

    @Test
    public void getIndexWordTest() {
        wordSet.addWord(before);
        assertEquals(before, wordSet.getIndexWord(0));
        wordSet.addWord(each);
        assertEquals(each, wordSet.getIndexWord(1));
    }

    @Test
    public void lookupWordBothNoMatchTest() {
        doubleAdd();
        assertTrue((wordSet.lookupWord("This is a string that matches no nothing")).isEmpty());
    }

    @Test
    public void lookupWordOneDefTest() {
        doubleAdd();
        Object word = (wordSet.lookupWord("to be last")).get(0);
        assertEquals(each, word);
    }

    @Test
    public void lookupWordOneWordTest() {
        doubleAdd();
        Object word = (wordSet.lookupWord("each")).get(0);
        assertEquals(each, word);
    }

    @Test
    public void lookupWordMultipleWordsTest() {
        doubleAdd();
        List<Word> list = wordSet.lookupWord("be");
        assertEquals(2, list.size());
        assertEquals(before, list.get(0));
        assertEquals(each, list.get(1));
    }

    private void doubleAdd() {
        wordSet.addWord(before);
        wordSet.addWord(each);
    }

    @Test
    public void initializeTest() {
        try {
            wordSet.initialize();
        } catch (IOException e) {
            fail();
        }

        List<Word> initialWords = wordSet.getTotalList();
        assertEquals("ah", (initialWords.get(0)).getWord());
        assertEquals("snowshoe", (initialWords.get(0)).getDefinition());
        assertEquals("do wejii", (initialWords.get(19)).getWord());
        assertEquals("an evil person", (initialWords.get(19)).getDefinition());
    }

    @Test
    public void addInitialWords() {
        String linetest = "test,line";
        wordSet.addInitialWords(linetest);
        assertEquals(1, (wordSet.getTotalList().size()));
    }

    @Test
    void testToJson() {
        wordSet.addWord(before);
        wordSet.addWord(each);
        WordSet wordSetTester;
        wordSetTester = reader.parseWordSet(wordSet.toJson(),"wordset");
        String word1 = (wordSetTester.getIndexWord(0)).getWord();
        String word2 = ((wordSet).getIndexWord(0)).getWord();
        assertEquals(word1, word2);
    }


}
