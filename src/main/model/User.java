package model;

// Represents the user. They have a hand of cards, a current score
// and a balance of chips (with arbitrary value). Their bet is also kept track of.
// When it is their turn, they bet an amount up to their balance.
// Then they can perform certain actions such as hit, stand, etc.
// Standing, doubling down or going bust ends their turn.
public class User extends Player {
    private int balance;
    private int bet;
    private boolean isDoubleDown;

    public static final int STARTING_BALANCE = 100;

    // EFFECTS: creates instance of user with an empty hand and STARTING_BALANCE chips to start with
    public User() {
        balance = STARTING_BALANCE;
        bet = 0;
        isDoubleDown = false;
    }

    // REQUIRES: amount > 0, amount <= balance, isTurn == true
    // MODIFIES: this
    // EFFECTS: places a bet; subtracts amount from balance
    public void placeBet(int amount) {
        bet = amount;
        balance -= amount;
    }

    // REQUIRES: this.balance >= this.bet, isTurn == true
    // MODIFIES: this
    // EFFECTS: doubles current bet, adds one card to hand, ends turn and returns the hand.
    // sets isDoubleDown to true
    public Hand doubleDown(Deck deck) {
        balance -= bet;
        bet += bet;
        hand.addCard(deck);
        score = hand.evaluate();
        isTurn = false;
        isDoubleDown = true;
        return hand;
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public boolean isDoubleDown() {
        return isDoubleDown;
    }

    public void setBalance(int amount) {
        balance = amount;
    }

    public void setBet(int amount) {
        bet = amount;
    }
}
