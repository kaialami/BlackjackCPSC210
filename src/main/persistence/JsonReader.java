package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// This code has been heavily modeled after CPSC 210's JSON Serialization Demo
// Repository: https://github.com/stleary/JSON-java.git

// Represents a reader that reads game state from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game state from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game state from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        GameState gs = new GameState(new User(), new Dealer(), new Deck());
        addUserDealerDeck(gs, jsonObject);
        return gs;
    }

    // MODIFIES: gs
    // EFFECTS: parses user, dealer, deck from JSON object and adds them to game state
    private void addUserDealerDeck(GameState gs, JSONObject jsonObject) {
        JSONObject jsonUser = (JSONObject) jsonObject.getJSONArray("user").get(0);
        User user = parseUser(jsonUser);
        JSONObject jsonDealer = (JSONObject) jsonObject.getJSONArray("dealer").get(0);
        Dealer dealer = parseDealer(jsonDealer);
        JSONObject jsonDeck = (JSONObject) jsonObject.getJSONArray("deck").get(0);
        Deck deck = parseDeck(jsonDeck);

        gs.setUser(user);
        gs.setDealer(dealer);
        gs.setDeck(deck);
    }

    // EFFECTS: parses and returns deck from JSON object
    private Deck parseDeck(JSONObject deckFields) {
        List<Card> newListOfCards = new ArrayList<>();
        JSONArray activeDeckArray = deckFields.getJSONArray("activeDeck");
        for (Object json : activeDeckArray) {
            JSONObject card = (JSONObject) json;
            int suit = card.getInt("suit");
            int value = card.getInt("value");
            newListOfCards.add(new Card(suit, value));
        }
        Deck deck = new Deck();
        deck.replaceDeckWith(newListOfCards);
        return deck;
    }

    // EFFECTS: parses and returns dealer from JSON object
    private Dealer parseDealer(JSONObject dealerFields) {
        int score = dealerFields.getInt("score");
        boolean isTurn = dealerFields.getBoolean("isTurn");
        Hand hand = parseHand(dealerFields);
        return new Dealer(score, isTurn, hand);
    }

    // EFFECTS: parses and returns user from JSON object
    private User parseUser(JSONObject userFields) {
        int score = userFields.getInt("score");
        int balance = userFields.getInt("balance");
        int bet = userFields.getInt("bet");
        boolean isTurn = userFields.getBoolean("isTurn");
        boolean isDoubleDown = userFields.getBoolean("isDoubleDown");
        Hand hand = parseHand(userFields);
        return new User(score, balance, bet, isTurn, isDoubleDown, hand);
    }

    // EFFECTS: parses and returns hand from a player's field JSON object
    private Hand parseHand(JSONObject playerFields) {
        JSONObject jsonHand = (JSONObject) playerFields.getJSONArray("hand").get(0);
        Hand hand = new Hand();
        JSONArray jsonCardArray = jsonHand.getJSONArray("cards");
        for (Object json : jsonCardArray) {
            JSONObject nextCard = (JSONObject) json;
            int suit = nextCard.getInt("suit");
            int value = nextCard.getInt("value");
            hand.addCard(new Card(suit, value));
        }
        return hand;
    }
}
