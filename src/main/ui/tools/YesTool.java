package ui.tools;

import ui.panels.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YesTool extends Tool {

    public YesTool(ToolPanel tp, JComponent parent) {
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
        button.setBackground(new Color(7, 255, 18));
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new YesToolClickHandler());
    }

    private class YesToolClickHandler implements ActionListener {
        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setActiveTool(YesTool.this);
        }
    }
}
