package ui;

import model.Dealer;
import model.Deck;
import model.GameState;
import model.User;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.GamePanel;
import ui.panels.TextPanel;
import ui.panels.ToolPanel;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlackjackPlayer extends JFrame {
    public static final int WIDTH = 850;
    public static final String JSON_STORE = "./data/gamestate.json";
    public static final int INTERVAL = 10;

    private User user;
    private Dealer dealer;
    private Deck deck;
    private boolean isDeal;

    private volatile boolean run;

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
        startBlackjack();
    }

    // MODIFIES: this
    // EFFECTS: manages startup of a new blackjack game
    private void startBlackjack() {
        while (run) {
            textPanel.startup();
            Tool activeTool = toolPanel.getActiveTool();
            if (activeTool != null) {
                if (activeTool.getLabel().equals("Yes")) {
                    run = false;
                    toolPanel.deactivateTool();
                    loadGameState();
                    waitForMS(1500);
                    playRound();
                } else if (activeTool.getLabel().equals("No")) {
                    System.exit(0);
                }
            }
        }
    }

    // EFFECTS: manages one round of play
    private void playRound() {
    }

    // MODIFIES: this
    // EFFECTS: manages if user wants to load game state before starting the first round of a new session
    private void loadGameState() {
        boolean loadGameState = isLoadGameState();
        if (loadGameState) {
            try {
                gs = jsonReader.read();
                user = gs.getUser();
                dealer = gs.getDealer();
                deck = gs.getDeck();
                textPanel.yesLoad();
            } catch (IOException e) {
                textPanel.loadFailIOE();
            } catch (JSONException e) {
                textPanel.loadFailUnsaved();
            }
        } else {
            textPanel.noLoad();
        }
    }

    // EFFECTS: handles user input on whether to load game state from JSON
    private boolean isLoadGameState() {
        boolean response = false;
        run = true;
        textPanel.load();
        while (run) {
            Tool activeTool = toolPanel.getActiveTool();
            if (activeTool != null) {
                if (activeTool.getLabel().equals("No")) {
                    response = true;
                }
                run = false;
            }
        }
        return response;
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

    // EFFECTS: sleeps for given number in ms
    private void waitForMS(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        }
    }
}
