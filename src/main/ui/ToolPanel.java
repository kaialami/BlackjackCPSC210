package ui;

import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ToolPanel extends JPanel {
    private static final int HEIGHT = 150;

    private List<Tool> tools;
    private Tool activeTool;

    public ToolPanel() {
        setPreferredSize(new Dimension(BlackjackPlayer.WIDTH, HEIGHT));
        setBackground(Color.gray);
    }

    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }
}
