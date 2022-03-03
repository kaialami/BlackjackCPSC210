//package persistence;
//
//
//import model.Dealer;
//import model.Deck;
//import model.GameState;
//import model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//// This code has been heavily modeled after CPSC 210's JSON Serialization Demo
//// Repository: https://github.com/stleary/JSON-java.git
//public class JsonWriterTest {
//    private User user;
//    private Dealer dealer;
//    private Deck deck;
//
//    @BeforeEach
//    public void setup() {
//        user = new User();
//        dealer = new Dealer();
//        deck = new Deck();
//    }
//
//    @Test
//    public void testWriterInvalidFile() {
//        try {
//            GameState gs = new GameState(user, dealer, deck);
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    public void testWriterDefaultGameState() {
//        try {
//            GameState gs = new GameState(user, dealer, deck);
//            JsonWriter writer = new JsonWriter("./data/testWriterDefaultGameState.json");
//            writer.open();
//            writer.write(gs);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterDefaultGameState.json");
//            gs = reader.read();
//            assertEquals(user, gs.getUser());
//            assertEquals(dealer, gs.getDealer());
//            assertEquals(deck, gs.getDeck());
//        } catch (IOException e) {
//            fail("Unexpected IOException");
//        }
//    }
//}
