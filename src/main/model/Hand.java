package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents player's/dealer's hand with a list of cards in the hand
public class Hand extends Writable {
    private List<Card> cards;

    // EFFECTS: creates instance of an empty hand
    public Hand() {
        cards = new ArrayList<>();
    }

    // MODIFIES: this, deck
    // EFFECTS: adds a random card from the given deck to the hand
    public void addCard(Deck deck) {
        cards.add(deck.removeCard());
    }

    // EFFECTS: evaluates and returns the score of the hand
    // if aces are present, evaluates to the largest score at or below 21.
    // returns -1 if hand is bust
    public int evaluate() {
        int score = 0;
        int numOfAcesHigh = 0;
        for (Card card : cards) {
            int cardValue = evaluateCard(card);
            if (cardValue == 11) {
                if (cardValue + score > 21) {
                    cardValue = 1;
                } else {
                    numOfAcesHigh++;
                }
            }
            score += cardValue;
            int[] results = handleAceBehaviour(score, numOfAcesHigh);
            score = results[0];
            numOfAcesHigh = results[1];
            if (score > 21) {
                return -1;
            }
        }

        return score;
    }

    // EFFECTS: evaluates the value of the given card
    public int evaluateCard(Card card) {
        int cardValue = card.getValue();
        if (cardValue > 10) {
            return 10;
        } else if (cardValue == 1) {
            return 11;
        } else {
            return cardValue;
        }
    }

    // MODIFIES: this
    // EFFECTS: manages converting aces high to aces low if high exceeds 21
    public int[] handleAceBehaviour(int score, int numOfAcesHigh) {
        int[] results = new int[2];
        while (score > 21 && numOfAcesHigh > 0) {
            score -= 10;
            numOfAcesHigh--;
        }
        results[0] = score;
        results[1] = numOfAcesHigh;
        return results;
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

    public List<Card> getCards() {
        return this.cards;
    }

    public int getSize() {
        return this.cards.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cards", cardsToJson());
        return json;
    }

    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Card card : cards) {
            jsonArray.put(objectToJson(card));
        }
        return jsonArray;
    }
}
