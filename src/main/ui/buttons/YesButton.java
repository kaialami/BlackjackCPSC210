package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YesButton extends Button {

    // EFFECTS: creates yes button
    public YesButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "Yes";
    }

    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Yes");
        button = customizeButton(button);
        button.setOpaque(true);
        button.setBackground(new Color(125, 255, 130));
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new YesToolClickHandler());
    }

    private class YesToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the yes button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            bp.setActiveButton(YesButton.this);
        }
    }
}
