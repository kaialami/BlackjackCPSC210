package ui;

import model.*;
import model.Event;
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

import static ui.panels.TextPanel.PLAY_AGAIN;

public class BlackjackPlayer extends JFrame {
    public static final int WIDTH = 900;
    public static final String JSON_STORE = "./data/gamestate.json";
    public static final int THRESHOLD = 20;

    private User user;
    private Dealer dealer;
    private Deck deck;

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
            textPanel.setText(TextPanel.WELCOME);
            Button activeButton = buttonPanel.getActiveButton();
            if (activeButton != null) {
                if (activeButton.getLabel().equals("Yes")) {
                    run = false;
                    buttonPanel.deactivateButton();
                    loadGameState();
                    sleepFor(1200);
                    playRound();
                } else if (activeButton.getLabel().equals("No")) {
                    printEvents();
                    System.exit(0);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: manages one round of play
    private void playRound() {
        dealer.setTurn(false);
        gamePanel.repaint();
        betOnRound("What is your bet?");
        clearButtons();
        sleepFor(800);
        checkIfShuffle();
        deal();
        run = true;
        userTurn();
        clearButtons();
        dealer.setTurn(true);
        if (user.getScore() == -1) {
            lose();
        } else {
            dealerTurn();
            if (dealer.getScore() == -1) {
                win();
            } else {
                checkScores();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: compares user and dealer scores
    private void checkScores() {
        if (user.getScore() > dealer.getScore()) {
            win();
        } else if (user.getScore() < dealer.getScore()) {
            lose();
        } else {
            tie();
        }
    }

    // MODIFIES: this
    // EFFECTS: process dealer turn
    private void dealerTurn() {
        while (dealer.getScore() < 17) {
            textPanel.setText(TextPanel.DEALER_TURN);
            sleepFor(600);
            doDealerHit();
            if (dealer.getScore() == -1) {
                break;
            }
        }
        if (dealer.getScore() != -1) {
            doDealerStand();
        }
    }

    // MODIFIES: this
    // EFFECTS: process dealer stand
    private void doDealerStand() {
        dealer.stand();
        textPanel.setText(TextPanel.STAND);
        dealer.setTurn(true);
        gamePanel.repaint();
        sleepFor(1200);
    }

    // MODIFIES: this
    // EFFECTS: process dealer hit
    private void doDealerHit() {
        dealer.hit(deck);
        textPanel.setText(TextPanel.HIT);
        dealer.setTurn(true);
        gamePanel.repaint();
        sleepFor(1200);
    }

    // MODIFIES: this
    // EFFECTS: ends round and asks if user wants to play again (if they are able to)
    private void lose() {
        textPanel.setText(TextPanel.LOSE);
        gamePanel.repaint();
        sleepFor(2500);
        resetPlayers();
        if (user.getBalance() > 0) {
            playAgain();
        } else {
            noMoney();
        }
    }

    // MODIFIES: this
    // EFFECTS: pays out 2 * bet to user and asks if play again
    private void win() {
        textPanel.setText(TextPanel.WIN);
        user.payout(2 * user.getBet());
        gamePanel.repaint();
        sleepFor(2500);
        resetPlayers();
        playAgain();
    }

    // EFFECTS: ends session since the user has no money :(
    private void noMoney() {
        String text = "<html>You're broke!<br>Why don't you come back when you're a little... richer.<html>";
        textPanel.setText(text);
        sleepFor(4000);
        printEvents();
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: pays out bet to user and asks if play again
    private void tie() {
        textPanel.setText(TextPanel.PUSH);
        user.payout(user.getBet());
        gamePanel.repaint();
        sleepFor(2500);
        resetPlayers();
        playAgain();
    }

    // MODIFIES: this
    // EFFECTS: resets player hands
    private void resetPlayers() {
        dealer.resetHand();
        user.resetHand();
    }

    // MODIFIES: this
    // EFFECTS: asks if play another round
    private void playAgain() {
        run = true;
        buttonPanel.createButtons(ButtonPanel.ButtonLayout.YES_NO);
        textPanel.setText("<html>" + textPanel.getJlabel().getText() + "<br>" + PLAY_AGAIN + "<html>");
        while (run) {
            Button activeButton = buttonPanel.getActiveButton();
            if (activeButton != null) {
                if (activeButton.getLabel().equals("Yes")) {
                    run = false;
                    buttonPanel.deactivateButton();
                    sleepFor(500);
                    playRound();
                } else if (activeButton.getLabel().equals("No")) {
                    buttonPanel.deactivateButton();
                    saveGameState();
                    printEvents();
                    System.exit(0);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: manages user turn
    private void userTurn() {
        createUserTurnButtons();
        while (run) {
            textPanel.setText(TextPanel.USER_TURN);
            Button activeButton = buttonPanel.getActiveButton();
            if (activeButton != null) {
                if (activeButton.getLabel().equals("Hit")) {
                    doUserHit();
                    if (user.getScore() == -1) {
                        run = false;
                    }
                } else if (activeButton.getLabel().equals("Stand")) {
                    doUserStand();
                    run = false;
                } else if (activeButton.getLabel().equals("Double Down")) {
                    doUserDoubleDown();
                    run = false;
                } else if (activeButton.getLabel().equals("Score")) {
                    doDisplayScore();
                }
                buttonPanel.deactivateButton();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates user turn buttons depending on ability to double down
    private void createUserTurnButtons() {
        if (user.getBalance() >= user.getBet()) {
            buttonPanel.createButtons(ButtonPanel.ButtonLayout.DD_FALSE);
        } else {
            buttonPanel.createButtons(ButtonPanel.ButtonLayout.DD_TRUE);
        }

    }

    // MODIFIES: this
    // EFFECTS: displays score and gives hint
    private void doDisplayScore() {
        user.checkScore();
        String hint = "Hint: ";
        if (user.getScore() >= 17) {
            hint += "Try standing here.";
        } else {
            hint += "Try hitting here.";
        }
        textPanel.setText("<html>Your hand's score is " + user.getScore() + ".<br>" + hint + "<html>");
        sleepFor(1500);
    }

    // MODIFIES: this
    // EFFECTS: process user double down
    private void doUserDoubleDown() {
        user.doubleDown(deck);
        textPanel.setText(TextPanel.DOUBLE_DOWN);
        gamePanel.repaint();
        sleepFor(600);
    }

    // MODIFIES: this
    // EFFECTS: process user stand
    private void doUserStand() {
        user.stand();
        textPanel.setText(TextPanel.STAND);
        gamePanel.repaint();
        sleepFor(600);
    }

    // MODIFIES: this
    // EFFECTS: process user hit
    private void doUserHit() {
        user.hit(deck);
        textPanel.setText(TextPanel.HIT);
        gamePanel.repaint();
        sleepFor(600);
        clearButtons();
        buttonPanel.createButtons(ButtonPanel.ButtonLayout.DD_TRUE);
    }

    // MODIFIES: this
    // EFFECTS: deals cards to user and dealer
    private void deal() {
        textPanel.setText(TextPanel.DEAL);
        deck.dealOneCard(dealer);
        gamePanel.repaint();
        sleepFor(200);
        deck.dealOneCard(dealer);
        gamePanel.repaint();
        sleepFor(200);
        deck.dealOneCard(user);
        gamePanel.repaint();
        sleepFor(200);
        deck.dealOneCard(user);
        gamePanel.repaint();
        sleepFor(800);
        user.setDoubleDown(false);
    }

    // MODIFIES: this
    // EFFECTS: clears buttons from window
    private void clearButtons() {
        buttonPanel.clearButtons();
        buttonPanel.deactivateButton();
    }

    // MODIFIES: this
    // EFFECTS: manages shuffling deck if its size is too low
    private void checkIfShuffle() {
        if (deck.getActiveDeck().size() < THRESHOLD) {
            textPanel.setText(TextPanel.SHUFFLING);
            deck.shuffle();
            sleepFor(1300);
        }
    }

    // MODIFIES: this
    // EFFECTS: manages betting on a round. input message is given msg
    private void betOnRound(String msg) {
        textPanel.setText(TextPanel.PLACE_YOUR_BET);
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
                initGUI();
                textPanel.setText(TextPanel.LOAD_SUCCESS);
            } catch (IOException e) {
                textPanel.setText(TextPanel.LOAD_FAIL_IOE);
            } catch (JSONException e) {
                textPanel.setText(TextPanel.LOAD_FAIL_JSONE);
            }
        } else {
            textPanel.setText(TextPanel.NO_LOAD);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles user input on whether to load game state from JSON
    private boolean isLoadGameState() {
        boolean response = false;
        run = true;
        textPanel.setText(TextPanel.LOAD);
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
    // EFFECTS: manages if user wants to save game state after a round has finished
    public void saveGameState() {
        if (user.getBalance() > 0) {
            boolean saveGameState = isSaveGameState();
            if (saveGameState) {
                try {
                    GameState gs = new GameState(user, dealer, deck);
                    jsonWriter.open();
                    jsonWriter.write(gs);
                    jsonWriter.close();
                    textPanel.setText("Saved game state to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    textPanel.setText("Unable to write to file " + JSON_STORE + " : file not found");
                }
            }
            textPanel.setText("Come again!");
            sleepFor(1000);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles user input on whether to save game state to JSON
    private boolean isSaveGameState() {
        boolean response = false;
        run = true;
        textPanel.setText(TextPanel.SAVE);
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
        gs = new GameState(user, dealer, deck);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initializes gui stuff
    private void initGUI() {
        getContentPane().removeAll();
        gamePanel = new GamePanel(gs);
        buttonPanel = new ButtonPanel(ButtonPanel.ButtonLayout.YES_NO);
        textPanel = new TextPanel();
        add(gamePanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        centreOnScreen();
        setVisible(true);
        repaint();
    }

    // EFFECTS: sleeps for given number in ms
    private void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException  e) {
            Thread.currentThread().interrupt();
        }
    }

    // EFFECTS: prints events to console
    private void printEvents() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Event log:");
        for (Event event : eventLog) {
            System.out.println(event.toString() + "\n");
        }
    }
}
