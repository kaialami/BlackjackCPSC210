package model;

import persistence.Writable;

import java.util.List;

// Represents a player in the game, i.e. the user or the dealer
// Player has a hand of cards and a corresponding score.
// On their turn, they can hit or stand. Standing or going bust ends their turn
public abstract class Player extends Writable {
    protected Hand hand;
    protected int score;
    protected boolean isTurn;

    // EFFECTS: creates instance of player gives them an empty hand. Their turn has not started yet
    public Player() {
        hand = new Hand();
        score = 0;
        isTurn = false;
    }

    // REQUIRES: 0 <= score <= 21
    // EFFECTS: creates player with specified fields
    public Player(int score, boolean isTurn, Hand hand) {
        this.score = score;
        this.isTurn = isTurn;
        this.hand = hand;
    }

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

    // MODIFIES: this
    // EFFECTS: ends player's turn
    public void stand() {
        isTurn = false;
    }

    // MODIFIES: this
    // EFFECTS: makes player's hand empty and score 0
    public void resetHand() {
        hand = new Hand();
        score = 0;
    }

    // EFFECTS: returns true if player has an ace
    public boolean hasAce() {
        List<Card> cards = hand.getCards();
        for (Card card : cards) {
            if (card.getValue() == 1) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: updates player score to match hand.
    public void updateScore() {
        score = hand.evaluate();
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

    public void setTurn(boolean bool) {
        isTurn = bool;
    }

}
