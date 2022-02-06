package model;

// Represents a player in the game, i.e. the user or the dealer
// Player has a hand of cards and a corresponding score.
// On their turn, they can hit or stand. Standing or going bust ends their turn
public class Player {
    Hand hand;
    int score;
    boolean isTurn;

    // EFFECTS: creates instance of player. starts their turn and gives them an empty hand.
    public Player() {
        hand = new Hand();
        score = 0;
        isTurn = true;
    }

    // REQUIRES: isTurn == true
    // MODIFIES: this, deck
    // EFFECTS: adds a card to hand, updates score, then returns hand. ends turn if bust.
    public Hand hit(Deck deck) {
        hand.addCard(deck);
        score = hand.evaluate();
        if (score == -1) {
            isTurn = false;
        }

        return hand;
    }

    // REQUIRES: isTurn == true
    // MODIFIES: this
    // EFFECTS: ends player's turn
    public void stand() {
        isTurn = false;
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
