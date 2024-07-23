package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Test class for ALlWords
class AllWordsTest {
    AllWords allwords;
    Word before;
    Word each;
    int day;
    int mod;

    @Test
    // EFFECTS: Produces a word of the day by using today's date mod the length of dic. list.
    public void wordOfDay() {
        allwords = new AllWords();
        before = new Word("before", "to be first");
        each = new Word("each", "to be last");
        day = Integer.parseInt(((LocalDate.now()).toString()).substring(8));
        mod = day % 2;

        allwords.addWord(before);
        allwords.addWord(each);
        Word modWord = allwords.getIndexWord(mod);
        String modWordOutput = (modWord.getWord() + ": " + modWord.getDefinition());
        assertEquals(modWordOutput, allwords.wordOfDay());
    }


}