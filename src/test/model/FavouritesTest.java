package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Test class for Favourites
class FavouritesTest {
    Favourites favourites;
    Word before;
    Word each;
    List<Word> masterList;
    List<Word> empty;


    @BeforeEach
    public void setup() {
        favourites = new Favourites();
        before = new Word("before", "to be first");
        each = new Word("each", "to be last");
        masterList = new ArrayList<Word>();
        empty = new ArrayList<Word>();

        masterList.add(before);
        masterList.add(each);
    }

    @Test
    public void addWordNoWordsTest() {
        assertFalse(favourites.addWord(empty, "before"));
    }

    @Test
    public void addWordMatchTest() {
        assertTrue(favourites.addWord(masterList, "before"));
        assertEquals(before, favourites.getIndexWord(0));
    }

    @Test
    public void addWordMultipleAddTest() {
        assertTrue(favourites.addWord(masterList, "before"));
        assertEquals(before, favourites.getIndexWord(0));
        assertTrue(favourites.addWord(masterList, "each"));
        assertEquals(each, favourites.getIndexWord(1));
    }

    @Test
    public void addWordNoMatchTest() {
        assertFalse(favourites.addWord(masterList, "toto"));
        assertEquals(0, (favourites.getTotalList()).size());
    }

}