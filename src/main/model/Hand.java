package model;

import java.util.ArrayList;
import java.util.List;

// Represents player's/dealer's hand with a list of cards in the hand
public class Hand {
    private List<Card> cards;

    // EFFECTS: creates instance of an empty hand
    public Hand() {
        cards = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a random card from the given deck to the hand and returns the hand
    public List<Card> addCard(Deck deck) {
        cards.add(deck.removeCard());
        return cards;
    }

    // EFFECTS: evaluates and returns the score of the hand
    // if aces are present, evaluates to the largest score at or below 21.
    // returns -1 if hand is bust
    public int evaluate() {
        int score = 0;
        for (Card card : cards) {
            int cardValue = card.getValue();
            if (cardValue >= 11) {
                score += 10;
            } else if (cardValue == 1) {
                if (11 + score > 21) {
                    score += 1;
                } else {
                    score += 11;
                }
            } else {
                score += cardValue;
            }

            if (score > 21) {
                score = -1;
                break;
            }
        }
        return score;
    }

    // MODIFIES: this
    // EFFECTS: three of spades to hand.
    // NOTE: this method is ONLY for testing purposes
    public void addThree() {
        cards.add(new Card(3, 3));
    }

    // MODIFIES: this
    // EFFECTS: adds queen of hearts to hand
    // NOTE: method is ONLY for testing purposes
    public void addQueen() {
        cards.add(new Card(2, 12));
    }

    // MODIFIES: this
    // EFFECTS: adds ace of diamonds to hand
    // NOTE: method is ONLY for testing purposes
    public void addAce() {
        cards.add(new Card(1, 1));
    }

    public List<Card> getHand() {
        return this.cards;
    }


}
