package ui.panels;

import ui.buttons.*;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Modelled after CPSC 210's SpaceInvaders and DrawingPlayer apps
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
public class ButtonPanel extends JPanel {
    public enum ButtonLayout { YES_NO, DD_TRUE, DD_FALSE }

    private List<Button> buttons;
    private Button activeButton;

    // EFFECTS: constructs button panel
    public ButtonPanel(ButtonLayout bl) {
        buttons = new ArrayList<>();
        activeButton = null;
        setSize(new Dimension(0, 0));
        setBackground(Color.white);
        setLayout(new GridLayout(0,1));
        createButtons(bl);
    }

    // getters
    public Button getActiveButton() {
        return activeButton;
    }

    // MODIFIES: this, tool
    // EFFECTS: sets given button as active
    public void setActiveButton(Button tool) {
        if (activeButton != null) {
            activeButton.deactivate();
        }
        tool.activate();
        activeButton = tool;
    }

    // MODIFIES: this
    // EFFECTS: deactivates the active button
    public void deactivateButton() {
        activeButton = null;
    }

    // MODIFIES: this
    // EFFECTS: declares and instantiates all buttons depending on given layout
    public void createButtons(ButtonLayout bl) {
        YesButton yesButton;
        NoButton noButton;
        DoubleDownButton doubleDownButton;
        switch (bl) {
            case YES_NO:
                yesButton = new YesButton(this, this);
                buttons.add(yesButton);

                noButton = new NoButton(this, this);
                buttons.add(noButton);
                break;
            case DD_TRUE:
                createHitStandButtons();

                doubleDownButton = new DoubleDownButton(this, this);
                buttons.add(doubleDownButton);
                break;
            case DD_FALSE:
                createHitStandButtons();
        }
    }

    // MODIFIES: this
    // EFFECTS: clears button list and removes button components
    public void clearButtons() {
        buttons.clear();
        removeAll();
    }

    // MODIFIES: this
    // EFFECTS: helper method for creating hit and stand buttons
    private void createHitStandButtons() {
        HitButton hitButton = new HitButton(this, this);
        buttons.add(hitButton);

        StandButton standButton = new StandButton(this, this);
        buttons.add(standButton);
    }
}
