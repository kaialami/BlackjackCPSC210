package model;

import org.json.JSONObject;

// Represents the dealer with a hand and score.
public class Dealer extends Player {
    // EFFECTS: creates instance of dealer with empty hand and score
    public Dealer() {}

    // REQUIRES: 0 <= score <= 21
    // EFFECTS: creates dealer with specified fields
    public Dealer(int score, boolean isTurn, Hand hand) {
        super(score, isTurn, hand);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        json.put("isTurn", isTurn);
        json.put("hand", objectToJson(hand));
        return json;
    }
}
