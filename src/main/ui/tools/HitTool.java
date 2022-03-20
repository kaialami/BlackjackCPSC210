package ui.tools;

import ui.panels.ToolPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitTool extends Tool {
    public static final String LABEL = "Hit";

    public HitTool(ToolPanel tp, JComponent parent) {
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
        button.addActionListener(new HitToolClickHandler());
    }

    private class HitToolClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveTool(HitTool.this);
        }
    }
}
