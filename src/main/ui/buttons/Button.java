package ui.buttons;

import ui.panels.ButtonPanel;

import javax.swing.*;

// Button and all button classes are modelled after CPSC 210's DrawingPlayer app Tool class
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git
public abstract class Button {
    protected JButton button;
    protected ButtonPanel bp;
    private boolean active;
    protected String label;

    public Button(ButtonPanel tp, JComponent parent) {
        this.bp = tp;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
        label = "";
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS: adds given button to the parent component
    private void addToParent(JComponent parent) {
        parent.add(button);
    }

    protected abstract void addListener();

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    public String getLabel() {
        return label;
    }
}
