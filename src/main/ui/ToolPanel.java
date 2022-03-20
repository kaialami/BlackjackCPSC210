package ui;

import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolPanel extends JPanel {
    public enum ToolLayout { YES_NO, DD_TRUE, DD_FALSE }

    private static final int HEIGHT = 150;

    private List<Tool> tools;
    private Tool activeTool;

    // EFFECTS: constructs tool panel
    public ToolPanel(ToolLayout tl) {
        tools = new ArrayList<>();
        activeTool = null;
        setSize(new Dimension(0, 0));
        setBackground(Color.white);
        setLayout(new GridLayout(0,1));
        createTools(tl);
    }

    // getters
    public Tool getActiveTool() {
        return activeTool;
    }

    // MODIFIES: this, tool
    // EFFECTS: sets given tool as active
    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all tools depending on given layout
    public void createTools(ToolLayout tl) {
        YesTool yesTool;
        NoTool noTool;
        DoubleDownTool doubleDownTool;
        switch (tl) {
            case YES_NO:
                yesTool = new YesTool(this, this);
                tools.add(yesTool);

                noTool = new NoTool(this, this);
                tools.add(noTool);
                break;
            case DD_TRUE:
                createHitStandTools();

                doubleDownTool = new DoubleDownTool(this, this);
                tools.add(doubleDownTool);
                break;
            case DD_FALSE:
                createHitStandTools();
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method for creating hit and stand tools
    private void createHitStandTools() {
        HitTool hitTool = new HitTool(this, this);
        tools.add(hitTool);

        StandTool standTool = new StandTool(this, this);
        tools.add(standTool);
    }
}
