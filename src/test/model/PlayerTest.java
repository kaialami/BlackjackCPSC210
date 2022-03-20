package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Deck deck;

    @BeforeEach
    public void setup() {
        player = new Dealer();
        deck = new Deck();
    }

    @Test
    public void testConstructorNoParam() {
        assertEquals(0, player.getHand().getSize());
        assertEquals(0, player.getScore());
        assertFalse(player.isTurn());
    }

    @Test
    public void testConstructorParam() {
        Player player2 = new Dealer(1, false, new Hand());
        assertEquals(1, player2.getScore());
        assertFalse(player2.isTurn());
        assertEquals(0, player2.getHand().getSize());
    }

    @Test
    public void testHitNoBust() {
        Hand hand = player.hit(deck);
        assertEquals(1, hand.getSize());
    }

    @Test
    public void testHitBust() {
        while (player.getScore() != -1) {
            player.hit(deck);
        }
        assertEquals(-1, player.getScore());
        assertFalse(player.isTurn());
    }

    @Test
    public void testHitBustLotsOfTimes() {
        for (int i = 0; i < 300; i++) {
            testHitBust();
        }
    }

    @Test
    public void testStand() {
        player.stand();
        assertFalse(player.isTurn());
    }

    @Test
    public void testSetTurn() {
        player.setTurn(true);
        assertTrue(player.isTurn());
        player.setTurn(false);
        assertFalse(player.isTurn());
    }

    @Test
    public void testResetHand() {
        player.hit(deck);
        player.resetHand();
        assertEquals(0, player.getHand().getSize());
        assertEquals(0, player.getScore());
    }

    @Test
    public void testHasAceHas() {
        player.hand.addAce();
        assertTrue(player.hasAce());
    }

    @Test
    public void testHasAceNoHas() {
        player.hand.addQueen();
        player.hand.addThree();
        assertFalse(player.hasAce());
    }
}
