package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

// Blackjack game
public class Blackjack {
    private User user;
    private Dealer dealer;
    private Deck deck;
    private Scanner input;
    private boolean run;
    private boolean isDeal;

    private static final String INVALID_SELECTION = "Invalid selection...";

    // EFFECTS: runs the Blackjack game
    public Blackjack() {
        run = true;
        isDeal = false;
        runBlackjack();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    public void init() {
        user = new User();
        dealer = new Dealer();
        deck = new Deck();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: runs game loop
    public void runBlackjack() {
        String command = null;

        init();

        while (run) {
            introMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("y")) {
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
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if deck size < 20, shuffle deck
    public void checkIfShuffle() {
        if (deck.getActiveDeck().size() < 20) {
            System.out.println("\nShuffling deck\n");
            deck.setActiveDeck(deck.getFullDeck());
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
            if (dealer.getScore() > 17) {
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
        }
    }

    // MODIFIES: this
    // EFFECTS: process user's bet before a round
    public void betOnRound() {
        String command = null;
        System.out.println("\nNew round. \nPlace a bet. Balance is " + Integer.toString(user.getBalance()));
        while (true) {
            int bet = 0;
            command = input.next();
            try {
                bet = Integer.parseInt(command);
                if (bet > user.getBalance() || bet <= 0) {
                    System.out.println("Invalid bet amount - Must be positive and less than your balance.");
                } else {
                    user.setBalance(user.getBalance() - bet);
                    user.setBet(bet);
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
        String command = null;
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
            String command = null;
            while (true) {
                command = input.next();
                command.toLowerCase();
                if (command.equals("y")) {
                    return true;
                } else if (command.equals("n")) {
                    System.out.println("Come again!");
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
    // EFFECTS: displays win message. adds bet * 2 to user's balance
    public void youWin() {
        System.out.println("\nYou win! You get " + Integer.toString(user.getBet() * 2) + " added back.");
        user.setBalance(user.getBalance() + user.getBet() * 2);
        resetPlayers();
    }

    // MODIFIES: this
    // EFFECTS: returns the bet back to the user.
    public void tieGame() {
        System.out.println("\nPush. You get your money back.");
        user.setBalance(user.getBalance() + user.getBet());
        resetPlayers();
    }

    // MODIFIES: this
    // EFFECTS: resets players to default
    public void resetPlayers() {
        dealer.resetHand();
        user.resetHand();
    }

    public void playAgain() {
        boolean newRound = isNewRound();
        if (newRound) {
            playRound();
        } else {
            run = false;
        }
    }
}
