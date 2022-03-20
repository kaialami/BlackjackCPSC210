package model;

import org.json.JSONObject;

// Represents the user. They have a hand of cards, a current score
// and a balance of chips (with arbitrary value). Their bet is also kept track of.
// When it is their turn, they bet an amount up to their balance.
// Then they can perform certain actions such as hit, stand, etc.
// Standing, doubling down or going bust ends their turn.
// Hitting once prevents doubling down.
public class User extends Player {
    private int balance;
    private int bet;
    private boolean isDoubleDown;

    public static final int STARTING_BALANCE = 100;

    // EFFECTS: creates instance of user with an empty hand and STARTING_BALANCE chips to start with,
    // has not doubled down
    public User() {
        balance = STARTING_BALANCE;
        bet = 0;
        isDoubleDown = false;
    }

    // REQUIRES: balance > 0, bet >= 0, 0 <= score <= 21
    // EFFECTS: creates instance of user with specified fields.
    public User(int score, int balance, int bet, boolean isTurn, boolean isDoubleDown, Hand hand) {
        this.score = score;
        this.balance = balance;
        this.bet = bet;
        this.isTurn = isTurn;
        this.isDoubleDown = isDoubleDown;
        this.hand = hand;
    }

    // REQUIRES: amount > 0, amount <= balance, isTurn == true
    // MODIFIES: this
    // EFFECTS: places a bet; subtracts amount from balance
    public void placeBet(int amount) {
        bet = amount;
        balance -= amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: pays out bet by amount and resets the bet
    public void payout(int amount) {
        bet = 0;
        balance += amount;
    }

    // REQUIRES: isTurn == true
    // MODIFIES: this, deck
    // EFFECTS: disables ability to double down and performs a hit by super's implementation
    public Hand hit(Deck deck) {
        isDoubleDown = true;
        return super.hit(deck);
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

    // MODIFIES: this
    // EFFECTS: resets hand and bet
    @Override
    public void resetHand() {
        super.resetHand();
        bet = 0;
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

    public void setDoubleDown(boolean bool) {
        isDoubleDown = bool;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        json.put("balance", balance);
        json.put("bet", bet);
        json.put("isDoubleDown", isDoubleDown);
        json.put("isTurn", isTurn);
        json.put("hand", objectToJson(hand));
        return json;
    }
}
