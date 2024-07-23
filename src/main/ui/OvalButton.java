package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

// Class that makes oval buttons
public class OvalButton extends JButton {

    private final Color buttonColor = new Color(255, 102, 0);

    //MODIFIES: this
    //EFFECTS: Instantiates ovalButton
    public OvalButton(String label) {
        super(label);
        // Makes the button's background transparent
        setContentAreaFilled(false);
        // Optional: Makes the border invisible
        setBorderPainted(false);
        // Optional: Removes the focus painting
        setFocusPainted(false);

        this.setBackground(buttonColor);
        this.setForeground(Color.BLACK);
        this.setFont(Font.getFont(Font.SANS_SERIF));

    }

    //MODIFIES: this
    //EFFECTS: Sets the colour of oval button
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }

    //MODIFIES: this
    //EFFECTS: Sets the color of Border
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    //MODIFIES: this
    //EFFECTS: This method checks if the click was inside the oval shape
    @Override
    public boolean contains(int x, int y) {
        Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return shape.contains(x, y);
    }
}