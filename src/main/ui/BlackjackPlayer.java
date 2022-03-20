package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.GamePanel;
import ui.panels.TextPanel;
import ui.panels.ButtonPanel;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlackjackPlayer extends JFrame {
    public static final int WIDTH = 900;
    public static final String JSON_STORE = "./data/gamestate.json";
    public static final int THRESHOLD = 20;

    private User user;
    private Dealer dealer;
    private Deck deck;
    private boolean isDeal;

    private volatile boolean run;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameState gs;

    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
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
            Button activeButton = buttonPanel.getActiveButton();
            if (activeButton != null) {
                if (activeButton.getLabel().equals("Yes")) {
                    run = false;
                    buttonPanel.deactivateButton();
                    loadGameState();
                    sleepFor(1200);
                    playRound();
                } else if (activeButton.getLabel().equals("No")) {
                    System.exit(0);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: manages one round of play
    private void playRound() {
        betOnRound("What is your bet?");
        clearButtons();
        checkIfShuffle();
        deal();
        userTurn();
    }

    // MODIFIES: this
    // EFFECTS: manages user turn
    private void userTurn() {

    }

    // MODIFIES: this
    // EFFECTS: deals cards. user and dealer hit twice to simulate dealing
    private void deal() {
        textPanel.deal();
        dealer.hit(deck);
        gamePanel.repaint();
        sleepFor(200);
        dealer.hit(deck);
        gamePanel.repaint();
        sleepFor(200);
        user.hit(deck);
        gamePanel.repaint();
        sleepFor(200);
        user.hit(deck);
        gamePanel.repaint();
        sleepFor(800);
        user.setDoubleDown(false);
    }

    // MODIFIES: this
    // EFFECTS: clears buttons from window
    private void clearButtons() {
        buttonPanel.clearButtons();
        sleepFor(800);
    }

    // MODIFIES: this
    // EFFECTS: manages shuffling deck if its size is too low
    private void checkIfShuffle() {
        if (deck.getActiveDeck().size() < THRESHOLD) {
            textPanel.shuffling();
            deck.shuffle();
            sleepFor(500);
        }
    }

    // MODIFIES: this
    // EFFECTS: manages betting on a round. input message is given msg
    private void betOnRound(String msg) {
        textPanel.placeBet();
        int bet;
        String result = JOptionPane.showInputDialog(this,
                msg + " (Balance is " + user.getBalance() + ")");
        try {
            bet = Integer.parseInt(result);
            if (bet > user.getBalance() || bet <= 0) {
                betOnRound("Invalid bet amount");
            } else {
                user.placeBet(bet);
                textPanel.setText("<html>Bet: " + user.getBet() + "<br>New Balance: " + user.getBalance() + "</html>");
            }
        } catch (NumberFormatException e) {
            betOnRound("Invalid bet amount");
        }
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
            Button activeButton = buttonPanel.getActiveButton();
            if (activeButton != null) {
                if (activeButton.getLabel().equals("Yes")) {
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
        buttonPanel = new ButtonPanel(ButtonPanel.ButtonLayout.YES_NO);
        textPanel = new TextPanel();
        add(gamePanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: sleeps for given number in ms
    private void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        }
    }
}
