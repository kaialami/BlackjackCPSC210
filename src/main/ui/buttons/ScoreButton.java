package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Displays current score and gives the user a hint on what they should play
public class ScoreButton extends Button {

    // EFFECTS: creates score button
    public ScoreButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "Score";
    }

    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("What's my score?");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new ScoreToolClickHandler());
    }

    private class ScoreToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the score button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            bp.setActiveButton(ScoreButton.this);
        }
    }
}
