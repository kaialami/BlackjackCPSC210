package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameStateTest {
    private GameState gs;

    @BeforeEach
    public void setup() {
        gs = new GameState();
    }

    @Test
    public void testConstructor() {
        assertEquals(null, gs.getUser());
        assertEquals(null, gs.getDeck());
    }

    @Test
    public void testSetUserOnce() {
        User user = new User();
        gs.setUser(user);
        assertEquals(user, gs.getUser());
    }

    @Test
    public void testSetUserMultipleTimes() {
        User user1 = new User();
        User user2 = new User();
        gs.setUser(user1);
        assertEquals(user1, gs.getUser());
        gs.setUser(user2);
        assertEquals(user2, gs.getUser());
    }
    @Test
    public void testSetDeckOnce() {
        Deck deck = new Deck();
        gs.setDeck(deck);
        assertEquals(deck, gs.getDeck());
    }

    @Test
    public void testSetDeckMultipleTimes() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        gs.setDeck(deck1);
        assertEquals(deck1, gs.getDeck());
        gs.setDeck(deck2);
        assertEquals(deck2, gs.getDeck());
    }



}
