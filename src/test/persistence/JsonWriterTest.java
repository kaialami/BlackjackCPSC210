package persistence;


import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

// This code has been heavily modeled after CPSC 210's JSON Serialization Demo
// Repository: https://github.com/stleary/JSON-java.git
public class JsonWriterTest {
    private User user;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    public void setup() {
        user = new User();
        dealer = new Dealer();
        deck = new Deck();
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            GameState gs = new GameState(user, dealer, deck);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterDefaultGameState() {
        try {
            GameState gs = new GameState(user, dealer, deck);
            JsonWriter writer = new JsonWriter("./data/testWriterDefaultGameState.json");
            writer.open();
            writer.write(gs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDefaultGameState.json");
            gs = reader.read();
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
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWriterChangedFields() {
        try {
            Hand userHand = new Hand();
            userHand.addCard(new Card(2, 3));
            user = new User(0, 1000, 44, true, false, userHand);
            dealer = new Dealer(0, true, new Hand());

            user.hit(deck);
            user.hit(deck);
            dealer.hit(deck);
            GameState gs = new GameState(user, dealer, deck);
            JsonWriter writer = new JsonWriter("./data/testWriterChangedFields.json");
            writer.open();
            writer.write(gs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterChangedFields.json");
            gs = reader.read();
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
            fail("Unexcpected IOException");
        }
    }
}
