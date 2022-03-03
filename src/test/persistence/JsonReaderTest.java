package persistence;

import model.GameState;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This code has been heavily modeled after CPSC 210's JSON Serialization Demo
// Repository: https://github.com/stleary/JSON-java.git
public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFileDummy.json");
        try {
            GameState gs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderDefaultGameState() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultGameState.json");
        try {
            GameState gs = reader.read();
            assertEquals(-1, gs.getUserBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderStoredBalanceGameState() {
        JsonReader reader = new JsonReader("./data/testReaderStoredBalanceGameState.json");
        try {
            GameState gs = reader.read();
            assertEquals(100, gs.getUserBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
