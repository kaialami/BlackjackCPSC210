package model;

// Represents the user. They have a hand of cards, a current score
// and a balance of chips (with arbitrary value). Their bet is also kept track of.
// When it is their turn, they bet an amount up to their balance.
// Then they can perform certain actions such as hit, stand, etc.
// Standing, doubling down or going bust ends their turn.
public class Player {

    // EFFECTS: creates instance of player with an empty hand and 100 chips to start with
    public Player() {

    }

    // REQUIRES: bet > 0
    // MODIFIES: this
    // EFFECTS: places a bet; subtracts amount from balance
    public void bet() {

    }

    // MODIFIES: this
    // EFFECTS: adds card from deck to hand, updates the hand's score, then returns the new hand
    public Hand hit(Deck deck) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: ends the player's turn
    public void stand() {

    }

    // MODIFIES: this
    // EFFECTS: doubles current bet, adds one card to hand, ends turn and returns the hand.
    public Hand doubleDown(Deck deck) {
        return null;
    }




}
