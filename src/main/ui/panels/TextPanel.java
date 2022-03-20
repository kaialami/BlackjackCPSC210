package ui.panels;

import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

import static ui.BlackjackPlayer.JSON_STORE;

public class TextPanel extends JPanel {
    private static final int HEIGHT = 150;
    private static final int FONT_SIZE = 20;
    private static final Font FONT = new Font("Cambria", Font.PLAIN, FONT_SIZE);

    public static final String WELCOME = "Welcome to Blackjack! Start game?";
    public static final String LOAD = "Load game from last save?";
    public static final String LOAD_SUCCESS = "Loaded game from " + JSON_STORE;
    public static final String LOAD_FAIL_IOE = "Unable to read from file " + JSON_STORE;
    public static final String LOAD_FAIL_JSONE = "Unable to load an unsaved game state. Loading default session...";
    public static final String NO_LOAD = "Loading default session...";
    public static final String PLACE_YOUR_BET = "Place your bet.";
    public static final String SHUFFLING = "Shuffling deck...";
    public static final String DEAL = "Dealing cards...";
    public static final String USER_TURN = "What will you do?";
    public static final String HIT = "Hit!";
    public static final String STAND = "Stand.";
    public static final String DOUBLE_DOWN = "Double down!";
    public static final String DEALER_TURN = "Dealer's turn...";
    public static final String WIN = "You win!";
    public static final String LOSE = "Game over! The dealer wins this one.";
    public static final String PUSH = "Push. It's a tie game.";
    public static final String PLAY_AGAIN = "Play again?";
    public static final String SAVE = "Save your game?";

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

    public JLabel getJlabel() {
        return jlabel;
    }
}
