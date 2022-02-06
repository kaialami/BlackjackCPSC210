package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Player.STARTING_BALANCE;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Deck deck;

    @BeforeEach
    public void setup() {
        player = new Player();
        deck = new Deck();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, player.getHand().getSize());
        assertEquals(STARTING_BALANCE, player.getBalance());
        assertEquals(0, player.getBet());
        assertTrue(player.isTurn());
    }

    @Test
    public void testPlaceBet() {
        player.placeBet(10);
        assertEquals(10, player.getBet());
        assertEquals(STARTING_BALANCE - 10, player.getBalance());
    }

    @Test
    public void testHit() {
        player.hit(deck);
        assertEquals(1, player.getHand().getSize());
    }

    @Test
    public void testStand() {
        player.stand();
        assertFalse(player.isTurn());
    }

    @Test
    public void testDoubleDown() {
        player.placeBet(10);
        player.doubleDown(deck);
        assertEquals(20, player.getBet());
        assertEquals(STARTING_BALANCE - 20, player.getBalance());
    }
}
