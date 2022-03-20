package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandButton extends Button {

    public StandButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "Stand";
    }

    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Stand");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new StandToolClickHandler());
    }

    private class StandToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the stand button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveButton(StandButton.this);
        }
    }


}
