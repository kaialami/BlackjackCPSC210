package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a deck of Cards with an active deck and full deck (for reference)
// The max amount of cards in the decks is 52
public class Deck extends Writable {
    private List<Card> activeDeck;

    // EFFECTS: creates instance of a full deck of cards
    public Deck() {
        this.activeDeck = generateFullDeck();
    }

    // EFFECTS: generates a list of all 52 cards
    public List<Card> generateFullDeck() {
        List<Card> deck = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            for (int k = 1; k < 14; k++) {
                Card card = new Card(j, k);
                deck.add(card);
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

    // MODIFIES: this
    // EFFECTS: re-generates full deck and assigns it to activeDeck
    public void shuffle() {
        activeDeck = generateFullDeck();
    }

    // MODIFIES: this
    // EFFECTS: replaces current active deck with new list of cards
    public void replaceDeckWith(List<Card> newActiveDeck) {
        activeDeck = newActiveDeck;
    }

    // MODIFIES: this, player
    // EFFECTS: deals one card to player and updates their score
    public void dealOneCard(Player player) {
        player.getHand().addCard(removeCard());
        player.updateScore();
    }

    public List<Card> getActiveDeck() {
        return this.activeDeck;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("activeDeck", cardsToJson());
        return json;
    }

    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Card card : activeDeck) {
            jsonArray.put(card.toJson());
        }
        return jsonArray;
    }
}
