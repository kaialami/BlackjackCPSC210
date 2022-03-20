package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitButton extends Button {

    public HitButton(ButtonPanel tp, JComponent parent) {
        super(tp, parent);
        label = "Hit";
    }

    // MODIFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Hit");
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new HitToolClickHandler());
    }

    private class HitToolClickHandler implements ActionListener {
        // EFFECTS: sets active button to the hit button
        //          called by the framework when the button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveButton(HitButton.this);
        }
    }
}
