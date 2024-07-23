package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Runs Program and creates start screen
public class StartScreen implements ActionListener {
    private static final String IMAGE = "./data/flag.png";
    private JFrame myFrame;
    private JPanel panel;
    private JPanel bigPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;
    private JButton startButton;
    private BufferedImage myPicture;
    private JLabel picLabel;
    private Color charcoal = new Color(18, 38, 32);


    // EFFECTS: Instantiates dictionary
    public  StartScreen() {
        createFrame();
    }

    //MODIFIES: this
    // EFFECTS: Creates frame for start screen
    private void createFrame() {
        myFrame = new JFrame("A Dictionary that Dictionaries");
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imagePanel = new JPanel();
        buttonPanel = new JPanel();
        startButton = new OvalButton("Start");
        bigPanel = new JPanel();

        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.PAGE_AXIS));
        imagePanel.setBackground(new Color(18, 38, 32));

        imageLoader();
        imagePanel.add(picLabel);
        bigPanel.add(imagePanel);
        startButton.addActionListener(this);
        buttonPanel.add(startButton);
        buttonPanel.setBackground(charcoal);
        bigPanel.add(buttonPanel);
        myFrame.add(bigPanel);

        myFrame.getContentPane().add(BorderLayout.CENTER, bigPanel);

        myFrame.setVisible(true);
        myFrame.pack();
        myFrame.setResizable(false);


    }

    //MODIFIES: this
    //EFFECTS: Helper method that loads in start image
    public void imageLoader() {
        try {
            myPicture = ImageIO.read(new File(IMAGE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        picLabel = new JLabel(new ImageIcon(myPicture));
    }

    // EFFECTS: Instantiates Dictionary if button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        new DictionaryApp();
    }
}
