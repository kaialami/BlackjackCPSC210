package ui.tools;

import ui.panels.ToolPanel;

import javax.swing.*;

// Tool and all tool classes are modelled after CPSC 210's DrawingPlayer app
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter.git
public abstract class Tool {
    protected JButton button;
    protected ToolPanel tp;
    private boolean active;

    public Tool(ToolPanel tp, JComponent parent) {
        this.tp = tp;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
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
}
