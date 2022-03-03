package model;

import org.json.JSONObject;

// Represents the dealer with a hand and score. The dealer will hit until their score is >= 17.
// NOTE: Dealer functions exactly as Player. Dealer was created just for naming purposes.
public class Dealer extends Player {
    // EFFECTS: creates instance of dealer with empty hand and score
    public Dealer() {}


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("score", score);
        json.put("isTurn", isTurn);
        json.put("hand", objectToJson(hand));
        return json;
    }
}
