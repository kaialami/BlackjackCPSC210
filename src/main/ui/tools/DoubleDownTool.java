package ui.tools;

import ui.panels.ToolPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleDownTool extends Tool {

    public DoubleDownTool(ToolPanel tp, JComponent parent) {
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
        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveTool(DoubleDownTool.this);
        }
    }
}
