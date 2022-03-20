package ui.panels;

import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

import static ui.BlackjackPlayer.JSON_STORE;

public class TextPanel extends JPanel {
    private static final int HEIGHT = 58;
    private static final int FONT_SIZE = 20;
    private static final Font FONT = new Font("Cambria", Font.PLAIN, FONT_SIZE);

    private static final String WELCOME = "Welcome to Blackjack! Start game?";
    private static final String LOAD = "Load game from last save?";
    private static final String LOAD_SUCCESS = "Loaded game from " + JSON_STORE;
    private static final String LOAD_FAIL_IOE = "Unable to read from file " + JSON_STORE;
    private static final String LOAD_FAIL_JSONE = "Unable to load an unsaved game state. Loading default session";
    private static final String NO_LOAD = "Loading default session";
    private static final String PLACE_YOUR_BET = "Place your bet";
    private static final String USER_TURN = "What will you do?";
    private static final String SHUFFLING = "Shuffling deck...";

    private JLabel jlabel;

    // EFFECTS: creates text panel with an empty text label
    public TextPanel() {
        setPreferredSize(new Dimension(BlackjackPlayer.WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        jlabel = new JLabel();
        jlabel.setFont(FONT);
        jlabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jlabel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: sets label to given string
    public void setText(String msg) {
        jlabel.setText(msg);
    }

    // MODIFIES: this
    // EFFECTS: sets "welcome" label
    public void startup() {
        jlabel.setText(WELCOME);
    }

    // MODIFIES: this
    // EFFECTS: sets "load game?" label
    public void load() {
        jlabel.setText(LOAD);
    }

    // MODIFIES: this
    // EFFECTS: sets "successful" load label
    public void yesLoad() {
        jlabel.setText(LOAD_SUCCESS);
    }

    // MODIFIES: this
    // EFFECTS: sets "file not found exception" label
    public void loadFailIOE() {
        jlabel.setText(LOAD_FAIL_IOE);
    }

    // MODIFIES: this
    // EFFECTS: sets "tried to load empty json" label
    public void loadFailUnsaved() {
        jlabel.setText(LOAD_FAIL_JSONE);
    }

    // MODIFIES: this
    // EFFECTS: sets "don't load game" label
    public void noLoad() {
        jlabel.setText(NO_LOAD);
    }

    // MODIFIES: this
    // EFFECTS: sets "place bet" label
    public void placeBet() {
        jlabel.setText(PLACE_YOUR_BET);
    }

    // MODIFIES: this
    // EFFECTS: sets "shuffling" label
    public void shuffling() {
        jlabel.setText(SHUFFLING);
    }
}
