package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a deck of Cards with an active deck and full deck (for reference)
// The max amount of cards in the decks is 52
public class Deck {
    private List<Card> fullDeck;
    private List<Card> activeDeck;

    // EFFECTS: creates instance of a full deck of cards
    public Deck() {
        this.fullDeck = generateFullDeck();
        this.activeDeck = this.fullDeck;
    }

    // EFFECTS: generates a list of all 52 cards
    public List<Card> generateFullDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            for (int j = 0; i < 4; i++) {
                for (int k = 1; k < 14; k++) {
                    Card card = new Card(j, k);
                    deck.add(card);
                }
            }
        }
        return deck;
    }

    // REQUIRES: deck size > 0
    // MODIFIES: this
    // EFFECTS: removes and returns random card from the deck
    public Card removeCard() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(activeDeck.size());
        return activeDeck.remove(randomIndex);
    }

    public List<Card> getActiveDeck() {
        return this.activeDeck;
    }

    public List<Card> getFullDeck() {
        return this.fullDeck;
    }
}