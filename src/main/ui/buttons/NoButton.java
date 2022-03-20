package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoButton extends Button {

    public NoButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "No";
    }


    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("No");
        button = customizeButton(button);
        button.setOpaque(true);
        button.setBackground(new Color(255, 75, 75));
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new NoToolClickHandler());
    }

    private class NoToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the no button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveButton(NoButton.this);
        }
    }
}

