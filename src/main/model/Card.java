package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a playing card with a suit and value
// suit is an integer in [0,3] where 0 = club, 1 = diamond, 2 = heart, 3 = spade
// value is an int in [1, 13]
//  - 1 = Ace
//  - 11 = Jack
//  - 12 = Queen
//  - 13 = King
// Note: ace still holds a value of 1 or 11 and the face cards all hold a value of 10
public class Card extends Writable {
    private int suit;
    private int value;

    // EFFECTS: creates instance of Card with a given suit and value
    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("suit", suit);
        json.put("value", value);
        return json;
    }
}
