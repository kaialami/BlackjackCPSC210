package persistence;

import jdk.jfr.Category;
import model.GameState;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

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
        String name = jsonObject.getString("name");
        GameState gs = new GameState();
        addUserAndDeck(gs, jsonObject);
        return gs;
    }

    // MODIFIES: gs
    // EFFECTS: parses user and deck from JSON object and adds them to game state
    private void addUserAndDeck(GameState gs, JSONObject jsonObject) {
        JSONArray jsonArrayUser = jsonObject.getJSONArray("user");
        JSONArray jsonArrayDeck = jsonObject.getJSONArray("deck");
        JSONObject theUser = (JSONObject) jsonArrayUser.get(0);
        addUser(gs, theUser);
        JSONObject theDeck = (JSONObject) jsonArrayDeck.get(0);
        addDeck(gs, theDeck);
    }

    // MODIFIES: gs
    // EFFECTS: parses user's balance from JSON object and adds it to game state
    private void addUser(GameState gs, JSONObject jsonObject) {
        String balance = jsonObject.getString("user");
        User user = new User();

    }

    private void addDeck(GameState gs, JSONObject jsonObject) {
    }
}
