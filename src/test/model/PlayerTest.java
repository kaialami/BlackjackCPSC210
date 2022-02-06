package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertEquals(0, player.getScore());
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
    public void testStand() {
        player.stand();
        assertFalse(player.isTurn());
    }

}
