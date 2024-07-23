package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test class for Word
public class WordTest {
    Word word;

    @BeforeEach
    public void setUp() {
        word = new Word();
        word.setWord("haryy");
        word.setDefinition("gogo");

    }

    @Test
    public void setWordTest() {
        assertEquals("haryy", word.getWord());
        word.setWord("rails");
        assertEquals("rails", word.getWord());
        word.setWord("toto");
        assertEquals("toto", word.getWord());
    }

    @Test
    public void getWordTest() {
        assertEquals("haryy", word.getWord());
    }

    @Test
    public void getDefinitionTest() {
        assertEquals("gogo", word.getDefinition());
    }

    @Test
    public void setDefinitionTest() {
        assertEquals("haryy", word.getWord());
        word.setWord("rails");
        assertEquals("rails", word.getWord());
        word.setWord("gram");
        assertEquals("gram", word.getWord());
    }
}
