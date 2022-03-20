package ui;

import model.Dealer;
import model.Deck;
import model.GameState;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.GamePanel;
import ui.panels.TextPanel;
import ui.panels.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class BlackjackPlayer extends JFrame {
    public static final int WIDTH = 850;
    private static final String JSON_STORE = "./data/gamestate.json";

    private User user;
    private Dealer dealer;
    private Deck deck;
    private boolean run;
    private boolean isDeal;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameState gs;

    private GamePanel gamePanel;
    private ToolPanel toolPanel;
    private TextPanel textPanel;

    // EFFECTS: constructs main window for blackjack
    public BlackjackPlayer() throws FileNotFoundException {
        super("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        initFields();
        initGUI();
        runBlackjack();
    }

    // EFFECTS: runs blackjack game
    private void runBlackjack() {
        while (run) {

        }
    }

    // MODIFIES: this
    // EFFECTS: centres frame on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: initializes fields.
    private void initFields() {
        deck = new Deck();
        user = new User();
        dealer = new Dealer();
        run = true;
        isDeal = false;
        gs = new GameState(user, dealer, deck);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes gui stuff
    private void initGUI() {
        gamePanel = new GamePanel(gs);
        toolPanel = new ToolPanel(ToolPanel.ToolLayout.YES_NO);
        textPanel = new TextPanel();
        add(gamePanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(toolPanel, BorderLayout.SOUTH);
        pack();
        centreOnScreen();
        setVisible(true);
    }


}
