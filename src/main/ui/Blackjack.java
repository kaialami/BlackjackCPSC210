package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Blackjack game
// Has a user, a dealer and a deck.
// run manages the game loop
// isDeal indicates when a "hit" is used to deal cards
public class Blackjack {
    private static final String JSON_STORE = "./data/gamestate.json";
    private User user;
    private Dealer dealer;
    private Deck deck;
    private Scanner input;
    private boolean run;
    private boolean isDeal;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameState gs;

    private static final String INVALID_SELECTION = "Invalid selection...";

    // EFFECTS: runs the Blackjack game
    public Blackjack() throws FileNotFoundException {
        run = true;
        isDeal = false;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBlackjack();
    }

    // MODIFIES: this
    // EFFECTS: initializes and instantiates objects
    public void init() {
        user = new User();
        dealer = new Dealer();
        deck = new Deck();
        gs = new GameState(user, dealer, deck);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: runs game loop
    public void runBlackjack() {
        String command;

        init();

        while (run) {
            introMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("y")) {
                loadGameState();
                playRound();
            } else if (command.equals("n")) {
                System.out.println("Okay bye");
                run = false;
            } else {
                System.out.println(INVALID_SELECTION + "\n");
            }
        }
    }

    // EFFECTS: prints intro menu
    public void introMenu() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("Start game? [y/n]");
    }

    // MODIFIES: this
    // EFFECTS: processes a single round of blackjack
    public void playRound() {
        checkIfShuffle();
        betOnRound();
        deal();
        userTurn();
        checkIfPlayerBust(user);
        if (run) {
            dealerTurn();
            checkIfPlayerBust(dealer);
            if (run) {
                if (user.getScore() > dealer.getScore()) {
                    youWin();
                } else if (user.getScore() < dealer.getScore()) {
                    gameOver();
                } else if (user.getScore() == dealer.getScore()) {
                    tieGame();
                }
                playAgain();
                saveGameState();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if deck size < 20, shuffle deck
    public void checkIfShuffle() {
        if (deck.getActiveDeck().size() < 20) {
            System.out.println("\nShuffling deck...\n");
            deck.shuffle();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user's turn.
    public void userTurn() {
        user.setTurn(true);
        while (user.isTurn()) {
            processUserTurn();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes dealer's turn
    public void dealerTurn() {
        System.out.println("\nDealer's turn\n");
        dealer.setTurn(true);
        while (dealer.isTurn()) {
            doHit(dealer);
            if (dealer.getScore() >= 17) {
                doStand(dealer);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if player went bust.
    public void checkIfPlayerBust(Player player) {
        if (player.getScore() == -1) {
            if (player.equals(dealer)) {
                youWin();
            } else if (player.equals(user)) {
                gameOver();
            }
            playAgain();
            saveGameState();
        }
    }

    // MODIFIES: this
    // EFFECTS: process user's bet before a round
    public void betOnRound() {
        String command;
        System.out.println("\nNew round. \nPlace a bet. Balance is " + Integer.toString(user.getBalance()));
        while (true) {
            int bet = 0;
            command = input.next();
            try {
                bet = Integer.parseInt(command);
                if (bet > user.getBalance() || bet <= 0) {
                    System.out.println("Invalid bet amount - Must be positive and less than your balance.");
                } else {
                    user.placeBet(bet);
                    System.out.println("Bet: " + user.getBet());
                    System.out.println("New Balance: " + user.getBalance());
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_SELECTION);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes the user's turn (hit, stand, double down)
    public void processUserTurn() {
        displayUserOptions();
        String command;
        while (true) {
            command = input.next();
            command.toLowerCase();

            if (command.equals("h")) {
                doHit(user);
                break;
            } else if (command.equals("s")) {
                doStand(user);
                break;
            } else if (command.equals("dd") && !user.isDoubleDown()) {
                doDoubleDown(user);
                break;
            } else {
                System.out.println(INVALID_SELECTION);
            }
        }
    }

    // MODIFIES: this, player
    // EFFECTS: player hits. new hand is printed.
    public void doHit(Player player) {
        System.out.println("Hit!");
        player.hit(deck);
        System.out.println(writeHand(player));
    }

    // MODIFIES: this, player
    // EFFECTS: player stands.
    public void doStand(Player player) {
        System.out.println("Stand");
        player.stand();
    }

    // MODIFIES: this, user
    // EFFECTS: user double downs. new hand is printed.
    public void doDoubleDown(User user) {
        System.out.println("Double down!");
        user.doubleDown(deck);
        System.out.println(writeHand(user));
    }

    // EFFECTS: display user's possible options during their turn
    public void displayUserOptions() {
        System.out.println("\nWhat will you do?");
        System.out.println("\th - hit");
        System.out.println("\ts - stand");
        if (!user.isDoubleDown()) {
            System.out.println("\tdd - double down");
        }
    }

    // MODIFIES: this
    // EFFECTS: dealer and user hit twice to simulate dealing
    public void deal() {
        isDeal = true;
        dealer.hit(deck);
        dealer.hit(deck);
        user.hit(deck);
        user.hit(deck);
        user.setDoubleDown(false);
        System.out.println("\nDealing cards...\n");
        System.out.println("Dealer's hand: " + writeHand(dealer));
        System.out.println("Your hand: " + writeHand(user));
        isDeal = false;
    }

    // EFFECTS: prints given player's hand of cards
    public String writeHand(Player player) {
        String cardsInHand = "";
        List<Card> cards = player.getHand().getCards();
        for (int i = 0; i < cards.size(); i++) {
            if (player.equals(dealer) && i == 0 && isDeal) {
                cardsInHand += "? ";
            } else {
                Card card = cards.get(i);
                int value = card.getValue();
                int suit = card.getSuit();
                String suitString = makeSuitString(suit);

                cardsInHand += writeCard(value, suitString);
            }

            if (i != cards.size()) {
                cardsInHand += ", ";
            }
        }
        return cardsInHand;
    }

    // EFFECTS: converts card's suit int to its string representation
    public String makeSuitString(int suit) {
        String suitString = "";
        if (suit == 0) {
            suitString = "♣";
        } else if (suit == 1) {
            suitString = "◆";
        } else if (suit == 2) {
            suitString = "♥";
        } else {
            suitString = "♠";
        }
        return suitString;
    }

    // EFFECTS: prints a single card's value + suit
    public String writeCard(int value, String suitString) {
        if (value == 1) {
            return "A " + suitString;
        } else if (value == 11) {
            return "J " + suitString;
        } else if (value == 12) {
            return "Q " + suitString;
        } else if (value == 13) {
            return "K " + suitString;
        } else {
            return Integer.toString(value) + " " + suitString;
        }
    }

    // MODIFIES: this
    // EFFECTS: asks user if they want to play a new round with their current balance, if possible.
    //          then returns their response
    public boolean isNewRound() {
        if (user.getBalance() <= 0) {
            System.out.println("\nYou have no money left. Come back when you're a little... richer.");
            return false;
        } else {
            System.out.println("\nPlay again? Balance is " + Integer.toString(user.getBalance()) + " [y/n]");
            String command;
            while (true) {
                command = input.next();
                command.toLowerCase();
                if (command.equals("y")) {
                    return true;
                } else if (command.equals("n")) {
                    run = false;
                    return false;
                } else {
                    System.out.println(INVALID_SELECTION);
                }
            }
        }
    }

    // EFFECTS: displays game over message.
    public void gameOver() {
        System.out.println("\nDealer wins... Better luck next time.");
        resetPlayers();
    }

    // MODIFIES: this
    // EFFECTS: displays win message. pays out double the bet to user
    public void youWin() {
        System.out.println("\nYou win! You get " + Integer.toString(user.getBet() * 2) + " added back.");
        user.payout(user.getBet() * 2);
        resetPlayers();
    }

    // MODIFIES: this
    // EFFECTS: returns the bet back to the user.
    public void tieGame() {
        System.out.println("\nPush. You get your money back.");
        user.payout(user.getBet());
        resetPlayers();
    }

    // MODIFIES: this
    // EFFECTS: resets players to default
    public void resetPlayers() {
        dealer.resetHand();
        user.resetHand();
    }

    // MODIFIES: this
    // EFFECTS: manages if user wants to play again, or is even able to.
    public void playAgain() {
        boolean newRound = isNewRound();
        if (newRound) {
            playRound();
        } else {
            run = false;
        }
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
                    System.out.println("Saved game state to " + JSON_STORE);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file " + JSON_STORE + " : file not found");
                }
            }
            System.out.println("\nCome again!");
        }
    }

    // EFFECTS: handles user input on whether to save game state to JSON
    private boolean isSaveGameState() {
        System.out.println("\nSave your progress? Balance is " + user.getBalance() + " [y/n]");
        String command;
        while (true) {
            command = input.next();
            command.toLowerCase();
            if (command.equals("y")) {
                return true;
            } else if (command.equals("n")) {
                return false;
            } else {
                System.out.println(INVALID_SELECTION);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: manages if user wants to load game state before starting the first round of a new session
    public void loadGameState() {
        boolean loadGameState = isLoadGameState();
        if (loadGameState) {
            try {
                gs = jsonReader.read();
                user = gs.getUser();
                dealer = gs.getDealer();
                deck = gs.getDeck();
                System.out.println("Loaded game state from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file " + JSON_STORE);
            } catch (JSONException e) {
                System.out.println("Unable to load an unsaved game state");
            }
        }
    }

    // EFFECTS: handles user input on whether to load game state from JSON
    private boolean isLoadGameState() {
        System.out.println("\nLoad game state from last save? [y/n]");
        String command;
        while (true) {
            command = input.next();
            command.toLowerCase();
            if (command.equals("y")) {
                return true;
            } else if (command.equals("n")) {
                return false;
            } else {
                System.out.println(INVALID_SELECTION);
            }
        }
    }
}
