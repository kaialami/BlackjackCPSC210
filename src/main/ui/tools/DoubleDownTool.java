package ui.tools;

import ui.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleDownTool extends Tool {
    public static final String LABEL = "Double Down";

    public DoubleDownTool(ToolPanel tp, JComponent parent) {
        super(tp, parent);
    }

    // MODOFIES: this
    // EFFECTS: creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(LABEL);
        button = customizeButton(button);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new DoubleDownToolClickHandler());
    }

    private class DoubleDownToolClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveTool(DoubleDownTool.this);
        }
    }
}
