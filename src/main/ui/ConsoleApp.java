package ui;

import model.Favourites;
import model.Word;
import model.AllWords;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Not supported. Console application
public class ConsoleApp {
    private static final String JSON_STORE = "./data/SavedDictionary.json";
    private AllWords allWords;
    private Scanner input;
    private Favourites favourites;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this.
    //EFFECTS: instantiates DictionaryApp
    public ConsoleApp() {
        allWords = new AllWords();
        favourites = new Favourites();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        try {
            allWords.initialize();
        } catch (IOException e) {
            System.out.println("Oh no, intial dataset not found");
        }
        runDictionary();
    }

    //MODIFIES: this.
    //EFFECTS: Displays main menu
    private void runDictionary() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        titleScreen();
        while (keepGoing) {
            command = (menuData()).toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    //MODIFIES: this.
    //EFFECTS: Prints main menu, returns keyboard input.
    public String menuData() {
        System.out.println("Select What you would like to do.");
        System.out.println("1 -> See list of all words");
        System.out.println("2 -> See list of favourite words");
        System.out.println("3 -> Word of the day");
        System.out.println("4 -> Add delete word or new word to Dictionary");
        System.out.println("q -> Quit");
        return input.next();
    }

    // MODIFIES: this.
    //EFFECTS: Runs the correct method for chosen menu input.
    private void processCommand(String command) {
        if (command.equals("1")) {
            printAllWords(allWords.getTotalList());
            totalListMenu();
        } else if (command.equals("2")) {
            runFavouritesMenu();
        } else if (command.equals("3")) {
            System.out.println("The word of the day is");
            System.out.println(allWords.wordOfDay());
        } else if (command.equals("4")) {
            runModifyList();
        } else if (command.equals("5")) {
            saveDictionary();
        } else if (command.equals("6")) {
            loadDictionary();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this., favourites, allwords
    //EFFECTS: Display favourites menu. Runs correct method for given input.
    public void runFavouritesMenu() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        while (keepGoing) {
            printFavouritesMenu();
            command = input.nextLine();

            if (command.equals("1")) {
                keepGoing = false;
            } else {
                if (command.equals("2")) {
                    List<Word> words = favourites.getTotalList();
                    for (Word w : words) {
                        System.out.println(w.getWord() + ": " + w.getDefinition());
                    }
                    System.out.println("");

                } else if (command.equals("3")) {
                    addFavourite();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }
    }

    //EFFECTS:  Prints  favourites menu
    private static void printFavouritesMenu() {
        System.out.println("Enter 1 to go back to previous menu ");
        System.out.println("Enter 2 to view favourite words");
        System.out.println("Enter 3 to add a word to favourites");
    }

    //MODIFIES: this., favourites
    //EFFECTS: Searches allWords for given word input, adds word to favorites if found. Prints if successful
    private void addFavourite() {
        String command;
        printAllWords(allWords.getTotalList());
        System.out.println("Enter word or copy a word from the list above and enter to add to favourites");
        command = input.nextLine();
        if (favourites.addWord(allWords.getTotalList(), command)) {
            System.out.println("Yay, your favourite word has been added! :)");
            System.out.println("");
        } else {
            System.out.println("Oh no, we couldn't your favourite word! :(");
            System.out.println("");
        }
    }

    //MODIFIES: this.
    //EFFECTS: Displays modifyList menu, runs correct method for given input
    private void runModifyList() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        while (keepGoing) {
            System.out.println("Enter 1 to go back to previous menu ");
            System.out.println("Enter 2 to add a word ");
            System.out.println("Enter 3 to delete a word");
            command = input.nextLine();

            if (command.equals("1")) {
                keepGoing = false;
            } else {
                if (command.equals("2")) {
                    addWord();
                } else if (command.equals("3")) {
                    deleteWord();
                } else {
                    System.out.println("Selection not valid...");
                }
            }
        }
    }

    //MODIFIES: this., allWords
    //EFFECTS: deletes word from allWords if found in allWords list
    private void deleteWord() {
        System.out.println("What is the word you want to delete?");
        String delete = input.nextLine();
        if (allWords.removeWord(delete)) {
            System.out.println("Word successfully deleted :)");
        } else {
            System.out.println("Oh no, we couldn't find that word. Check for spelling errors.");
        }
    }

    //MODIFIES: this., allWords, Word
    //EFFECTS: adds given word and description to a new Word, and adds Word to allWords
    private void addWord() {
        Word newWord = new Word();
        System.out.println("What is the word you want to add?");
        String word = input.nextLine();
        System.out.println("What is " + word + "'s definition?");
        String definition = input.nextLine();

        newWord.setWord(word);
        newWord.setDefinition(definition);
        allWords.addWord(newWord);
    }

    //MODIFIES: this.
    //EFFECTS: Searches allWords for given input. Prints the word and definition for all matches
    public void totalListMenu() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String search;

        while (keepGoing) {
            System.out.println("Enter 1 to go back to previous screen or"
                    + " enter word to look up definition/translation.");
            search = input.nextLine();

            if (search.equals("1")) {
                keepGoing = false;
            } else {
                List<Word> matches = allWords.lookupWord(search);
                for (Word m : matches) {
                    System.out.println(m.getWord() + ": " + m.getDefinition());
                }
            }
        }
    }

    //EFFECTS: Prints every word in every Word in allWords.
    public void printAllWords(List<Word> wordList) {
        for (Word w : wordList) {
            System.out.println(w.getWord());
        }
    }

    // EFFECTS: Prints title screen. Handles if JSON file cannot be found or written to.
    private void saveDictionary() {
        try {
            jsonWriter.open();
            jsonWriter.write(allWords, favourites);
            jsonWriter.close();
            System.out.println("Saved Dictionary to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: AllWords, Favourites
    // EFFECTS: Prints title screen. Handles if JSON file is improperly formatted
    private void loadDictionary() {
        try {
            allWords = (AllWords) jsonReader.read("allwords");
            favourites = (Favourites) jsonReader.read("favourites");
            System.out.println("Loaded Dictionary from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: Prints title screen.
    private void titleScreen() {
        System.out.println(" _________  __    _          __                                       \n"
                + "|  _   _  |[  |  (_)        [  |                                      \n"
                + "|_/ | | \\_| | |  __   .---.  | |--.   .--.                            \n"
                + "    | |     | | [  | / /'`\\] | .-. |/ .'`\\ \\                          \n"
                + "   _| |_    | |  | | | \\__.  | | | || \\__. |                          \n"
                + " _|_____|  [___][___]'.___.'[___]|__]'.__.'                           \n"
                + "|_   _ `.  (_)        / |_ (_)                                        \n"
                + "  | | `. \\ __   .---.`| |-'__   .--.   _ .--.   ,--.   _ .--.  _   __ \n"
                + "  | |  | |[  | / /'`\\]| | [  |/ .'`\\ \\[ `.-. | `'_\\ : [ `/'`\\][ \\ [  ]\n"
                + " _| |_.' / | | | \\__. | |, | || \\__. | | | | | // | |, | |     \\ '/ / \n"
                + "|______.' [___]'.___.'\\__/[___]'.__.' [___||__]\\'-;__/[___]  [\\_:  /  \n"
                + "                                                              \\__.'   ");
    }


}