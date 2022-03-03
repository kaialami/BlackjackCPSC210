package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameStateTest {
    private GameState gs;
    private User user;
    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    public void setup() {
        user = new User();
        dealer = new Dealer();
        deck = new Deck();
        gs = new GameState(user, dealer, deck);
    }

    @Test
    public void testConstructor() {
        assertEquals(user, gs.getUser());
        assertEquals(dealer, gs.getDealer());
        assertEquals(deck, gs.getDeck());
    }

    @Test
    public void testSetUser() {
        User newUser = new User();
        gs.setUser(newUser);
        assertEquals(newUser, gs.getUser());
    }

    @Test
    public void testSetDealer() {
        Dealer newDealer = new Dealer();
        gs.setDealer(newDealer);
        assertEquals(newDealer, gs.getDealer());
    }

    @Test
    public void testSetDeck() {
        Deck newDeck = new Deck();
        gs.setDeck(newDeck);
        assertEquals(newDeck, gs.getDeck());
    }
}
