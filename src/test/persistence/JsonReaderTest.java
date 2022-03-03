package persistence;

import model.Dealer;
import model.Deck;
import model.GameState;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
            User user = gs.getUser();
            Dealer dealer = gs.getDealer();
            Deck deck = gs.getDeck();

            assertEquals(User.STARTING_BALANCE, user.getBalance());
            assertEquals(0, user.getHand().getSize());
            assertEquals(0, user.getScore());
            assertFalse(user.isTurn());
            assertEquals(0, user.getBet());

            assertEquals(0, dealer.getHand().getSize());
            assertEquals(0, dealer.getScore());
            assertFalse(dealer.isTurn());

            assertEquals(52, deck.getActiveDeck().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderChangedFields() {
        JsonReader reader = new JsonReader("./data/testReaderChangedFields.json");
        try {
            GameState gs = reader.read();
            User user = gs.getUser();
            Dealer dealer = gs.getDealer();
            Deck deck = gs.getDeck();

            assertEquals(1000, user.getBalance());
            assertEquals(3, user.getHand().getSize());
            assertFalse(0 == user.getScore());
            assertTrue(user.isTurn());
            assertEquals(44, user.getBet());

            assertEquals(1, dealer.getHand().getSize());
            assertFalse(0 == dealer.getScore());
            assertTrue(dealer.isTurn());

            assertEquals(49, deck.getActiveDeck().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
