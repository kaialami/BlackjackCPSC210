package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleDownButton extends Button {

    public DoubleDownButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "Double Down";
    }

    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Double Down");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new DoubleDownToolClickHandler());
    }

    private class DoubleDownToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the double down button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveButton(DoubleDownButton.this);
        }
    }
}
