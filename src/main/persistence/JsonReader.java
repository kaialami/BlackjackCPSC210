//package persistence;
//
//import model.Dealer;
//import model.Deck;
//import model.GameState;
//import model.User;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//// This code has been heavily modeled after CPSC 210's JSON Serialization Demo
//// Repository: https://github.com/stleary/JSON-java.git
//
//// Represents a reader that reads game state from JSON data stored in a file
//public class JsonReader {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads game state from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public GameState read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseGameState(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses game state from JSON object and returns it
//    private GameState parseGameState(JSONObject jsonObject) {
//        GameState gs = new GameState(new User(), new Dealer(), new Deck());
//        addUserDealerDeck(gs, jsonObject);
//        return gs;
//    }
//
//    // MODIFIES: gs
//    // EFFECTS: parses user, dealer, deck from JSON object and adds them to game state
//    private void addUserDealerDeck(GameState gs, JSONObject jsonObject) {
//        addUser(gs, (JSONObject) jsonObject.get("user"));
//        addDealer(gs, (JSONObject) jsonObject.get("dealer"));
//        addDeck(gs, (JSONObject) jsonObject.get("deck"));
//    }
//
//    // MODIFIES: gs
//    // EFFECTS: parses deck from JSON object and adds it to game state
//    private void addDeck(GameState gs, JSONObject jsonObject) {
//        Deck deck = (Deck) jsonObject.get("deck");
//        gs.setDeck(deck);
//    }
//
//    // MODIFIES: gs
//    // EFFECTS: parses dealer from JSON object and adds it to game state
//    private void addDealer(GameState gs, JSONObject jsonObject) {
//        Dealer dealer = (Dealer) jsonObject.get("dealer");
//        gs.setDealer(dealer);
//    }
//
//    // MODIFIES: gs
//    // EFFECTS: parses user from JSON object and adds it to game state
//    private void addUser(GameState gs, JSONObject jsonObject) {
//        JSONArray userFields = jsonObject.getJSONArray("user");
//        for (Object field : userFields) {
//            JSONObject nextField = (JSONObject) json;
//        }
//
//        gs.setUser(user);
//    }
//}
