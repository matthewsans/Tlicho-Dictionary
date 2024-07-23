package ui;

import model.Word;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

// Represents the searched window
public class Search implements ListSelectionListener {
    private JFrame myFrame;
    private JPanel listPanel;
    private JTextArea textArea;
    private DefaultListModel searchModel;
    private JList searchedList;
    private JScrollPane scroller;
    private Color cream = new Color(255, 254, 230);

    //MODIFIES: this
    // EFFECTS: Instantiates search window
    public Search(ArrayList list) {
        myFrame = new JFrame("Results");
        searchModel = new DefaultListModel();
        textArea = new JTextArea(15, 50);
        listPanel = new JPanel();
        searchedList = new JList(searchModel);
        scroller = new JScrollPane(searchedList);

        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listPanel.setBackground(cream);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(Font.getFont(Font.SANS_SERIF));


        listSetup(list);
        myFrame.add(listPanel);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets up list to display
    private void listSetup(ArrayList list) {
        for (Object w : list) {
            Word q = (Word) w;
            searchModel.addElement(q.getWord() + ": " + q.getDefinition());
        }

        searchedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchedList.setSelectedIndex(0);
        searchedList.addListSelectionListener(this);
        searchedList.setVisibleRowCount(10);
        searchedList.setFont(Font.getFont(Font.SANS_SERIF));
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listPanel.add(scroller);
        scroller.setPreferredSize(new Dimension(500,300));
        searchedList.setBackground(cream);

    }

    //EFFECTS: required by ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
