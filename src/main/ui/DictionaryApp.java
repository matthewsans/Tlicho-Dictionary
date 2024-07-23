package ui;

import model.AllWords;
import model.Favourites;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//MAIN GUI class
public class DictionaryApp implements ActionListener, ListSelectionListener, DocumentListener {

    private static final String JSON_STORE = "./data/SavedDictionary.json";
    private MyFrame myFrame;
    private JFrame addFrame;
    private JButton add;
    private JButton delete;
    private JButton wordOfDay;
    private JButton save;
    private JButton load;
    private JButton newWindowAdd;
    private JButton search;
    private JList list;
    private JTextField userText;
    private AllWords wordSet;
    private Favourites favourites;
    private DefaultListModel listModel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField newWindowWord;
    private JTextField newWindowDefinition;
    private Color cream = new Color(255, 254, 230);
    private Color myBrown = new Color(182, 141, 64);
    private Color charcoal = new Color(18, 38, 32);


    // EFFECTS: Instantiates GUI
    public DictionaryApp() {
        myFrame = new MyFrame();
        wordSet = new AllWords();
        favourites = new Favourites();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        try {
            wordSet.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addTop();
        addButtons();
        addList();
        myFrame.setVisible(true);


    }

    //EFFECTS: Handles button logic
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            createFrame(this);
        }
        if (e.getSource() == delete) {
            processDelete();
        }
        if (e.getSource() == load) {
            loadDictionary();
            listModel.clear();
            updateModel();
            list.setSelectedIndex(0);

        }
        if (e.getSource() == save) {
            saveDictionary();
        }
        if (e.getSource() == newWindowAdd) {
            processNewWindowWord();
            list.setSelectedIndex(0);
        }
        if (e.getSource() == search) {
            searchBar();
        }
    }

    //MODIFIES: this, AllWords
    //EFFECTS: Adds new word to dictionary
    private void processNewWindowWord() {
        String word = newWindowWord.getText();
        String def = newWindowDefinition.getText();
        wordSet.addWord(new Word(word, def));
        listModel.clear();
        updateModel();
        addFrame.dispose();
    }

    //MODIFIES: this, AllWords
    //EFFECTS: Deletes word from dictionary
    private void processDelete() {
        int index = list.getSelectedIndex();
        listModel.remove(index);

        int size = listModel.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            delete.setEnabled(false);

        } else { //Select an index.
            if (index == listModel.getSize()) {
                //removed item in last position
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
        deleteWordFromDictionary(index);
    }

    //MODIFIES: this
    //EFFECTS: Makes all buttons in the bottom
    private void addButtons() {
        instantiateButtons();

        myFrame.getButtons().add(add);
        myFrame.getButtons().add(Box.createHorizontalStrut(5));
        myFrame.getButtons().add(delete);
        myFrame.getButtons().add(Box.createHorizontalStrut(5));
        myFrame.getButtons().add(load);
        myFrame.getButtons().add(Box.createHorizontalStrut(5));
        myFrame.getButtons().add(save);
        myFrame.getButtons().add(Box.createHorizontalStrut(5));
        //TODO
        // myFrame.getButtons().add(wordOfDay);


    }


    //MODIFIES: this
    //EFFECTS: adds top section panel. add search bar
    private void addTop() {
        userText = new JTextField(50);
        //userText.addActionListener(hireListener);
        //userText.getDocument().addDocumentListener(hireListener);
        myFrame.getTop().add(userText);
        search = new OvalButton("Search");
        search.addActionListener(this);
        myFrame.getTop().add(search);


    }

    // MODIFIES: this.
    // EFFECTS: instantiates buttons
    private void instantiateButtons() {
        Color buttonColor = new Color(255, 80, 80);
        add = new OvalButton("Add");
        delete = new OvalButton("Delete");
        save = new OvalButton("Save");
        load = new OvalButton("Load");
        wordOfDay = new OvalButton("Word of the Day");

        add.addActionListener(this);
        delete.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
    }

    //MODIFIES: this
    // EFFECTS: Adds list components to bottom panel
    public void addList() {

        listModel = new DefaultListModel();
        updateModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        list.setFont(Font.getFont(Font.SANS_SERIF));

        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(200, 150));
        list.setBackground(cream);
        myFrame.getScroll().add(listScrollPane);
    }

    //MODIFIES: this
    //EFFECTS: Updates model from allWords
    private void updateModel() {
        for (Object w : (wordSet.getTotalList())) {
            Word q = (Word) w;
            listModel.addElement(q.getWord() + ": " + q.getDefinition());
        }

    }

    //MODIFIES: this
    //EFFECTS: Sets delete activity. This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                delete.setEnabled(false);

            } else {
                delete.setEnabled(true);
            }
        }
    }

    //EFFECTS: Required by Document listener
    @Override
    public void insertUpdate(DocumentEvent e) {
    }

    //EFFECTS: Required by Document listener
    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    //EFFECTS: Required by Document listener
    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    //MODIFIES: AllWords
    //EFFECTS: Updates AllWords with current model
    public void updateDictionary() {
        AllWords words = new AllWords();
        Word word = new Word();
        for (int i = 0; i < listModel.getSize(); i++) {
            String o = (String) listModel.getElementAt(i);
            String[] sep = o.split("\\:");
            words.addWord(new Word(sep[0], sep[1]));
        }

        wordSet = words;
    }

    //MODIFIES: AllWords
    //EFFECTS: deletes a word from AllWords
    public void deleteWordFromDictionary(int index) {
        ArrayList temp = (ArrayList) wordSet.getTotalList();
        Word word = new Word();
        String o = (String) listModel.getElementAt(index);
        String[] sep = o.split("\\:");
        wordSet.removeWord(sep[0]);
    }

    // EFFECTS: Prints title screen. Handles if JSON file cannot be found or written to.
    private void saveDictionary() {
        try {
            jsonWriter.open();
            jsonWriter.write(wordSet, favourites);
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
            wordSet = (AllWords) jsonReader.read("allwords");
            favourites = (Favourites) jsonReader.read("favourites");
            System.out.println("Loaded Dictionary from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates new window
    //  OriginalImplementation
    //  by https://stackoverflow.com/questions/15513380/how-to-open-a-new-window-by-clicking-a-button
    public void createFrame(DictionaryApp dictionaryApp) {
        addFrame = new JFrame("Add a Word :0");
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.setBackground(new Color(18, 38, 32));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        newWindowWord = new JTextField(20);
        inputPanel.add(new JLabel("Word"));
        newWindowAdd = new OvalButton("Enter");
        newWindowAdd.addActionListener(dictionaryApp);
        inputPanel.add(newWindowWord);
        panel.add(inputPanel);
        inputPanel.setBackground(myBrown);
        JPanel inputPanel2 = new JPanel();
        inputPanel2.setBackground(myBrown);
        frameSetup(inputPanel2, panel);

    }

    //MODIFIES: this
    //EFFECTS: helper method for frame
    private void frameSetup(JPanel inputPanel2, JPanel panel) {
        newWindowDefinition = new JTextField(20);
        inputPanel2.add(new JLabel("Definition"));
        inputPanel2.add(newWindowDefinition);
        panel.add(inputPanel2);
        panel.add(newWindowAdd);
        newWindowWord.setBackground(cream);
        newWindowDefinition.setBackground(cream);

        addFrame.getContentPane().add(BorderLayout.CENTER, panel);
        addFrame.pack();
        addFrame.setLocationByPlatform(true);
        addFrame.setVisible(true);
        addFrame.setResizable(false);
    }

    //MODIFIES: this
    // EFFECTS: helper for search action button. Sets up frame with searched words
    public void searchBar() {
        String text = userText.getText();
        ArrayList temp = (ArrayList) wordSet.lookupWord(text);
        System.out.println(temp);
        new Search(temp);
    }


}
