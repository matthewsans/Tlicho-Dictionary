package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame {

    private JPanel top;
    private JPanel scroll;
    private JPanel buttons;
    private Color cream;
    private Color myBrown;
    private Color charcoal;

    // Main frame of dictionary.
    MyFrame() {
        this.setTitle("Dictionary");

        this.setResizable(true);
        this.setSize(750, 800);
        this.setLayout(new BorderLayout());

        this.getContentPane().setBackground(new Color(123, 51, 250));
        setPanels();
        this.add(top, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event: eventLog) {
                    System.out.println(event.toString());
                }
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // EFFECTS: Returns top
    public JPanel getTop() {
        return top;
    }

    // EFFECTS: Returns scroll
    public JPanel getScroll() {
        return scroll;
    }

    // EFFECTS: Returns buttons.s
    public JPanel getButtons() {
        return buttons;
    }

    //EFFECTS: sets up the panels for main frame
    public void setPanels() {
        top = new JPanel();
        scroll = new JPanel();
        buttons = new JPanel();
        cream = new Color(255, 254, 230);
        myBrown = new Color(182, 141, 64);
        charcoal = new Color(18, 38, 32);

        top.setBackground(myBrown);
        scroll.setBackground(cream);
        buttons.setBackground(charcoal);

        top.setPreferredSize(new Dimension(100,100));
        scroll.setPreferredSize(new Dimension(100,100));
        buttons.setPreferredSize(new Dimension(100,100));
        buttons.setLayout(new FlowLayout());
        scroll.setLayout(new BorderLayout());
        top.add(buttons, BorderLayout.SOUTH);
    }
}
