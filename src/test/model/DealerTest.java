package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Dealer functions exactly as Player does so tests are redundant
public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    public void setup() {
        dealer = new Dealer();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, dealer.getHand().getSize());
        assertEquals(0, dealer.getScore());
        assertFalse(dealer.isTurn());
    }
}
