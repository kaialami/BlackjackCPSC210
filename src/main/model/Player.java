package model;

// Represents a player in the game, i.e. the user or the dealer
// Player has a hand of cards and a corresponding score.
// On their turn, they can hit or stand. Standing or going bust ends their turn
public class Player {
    private Hand hand;
    private int score;
    private boolean isTurn;

    // EFFECTS: creates instance of player. starts their turn and gives them an empty hand.
    public Player() {

    }

    // REQUIRES: isTurn == true
    // MODIFIES: this
    // EFFECTS: adds a card to hand, updates score, then returns hand
    public Hand hit() {
        return null;
    }

    // REQUIRES: isTurn == true
    // MODIFIES: this
    // EFFECTS: ends player's turn
    public void stand() {

    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public boolean isTurn() {
        return isTurn;
    }
}
