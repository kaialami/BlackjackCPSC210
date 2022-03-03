//package persistence;
//
//import model.GameState;
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
//        GameState gs = new GameState();
//        addUserBalance(gs, jsonObject);
//        return gs;
//    }
//
//    // MODIFIES: gs
//    // EFFECTS: parses user's balance from JSON object and adds it to game state
//    private void addUserBalance(GameState gs, JSONObject jsonObject) {
//        int balance = jsonObject.getInt("userBalance");
//        gs.setUserBalance(balance);
//    }
//}
