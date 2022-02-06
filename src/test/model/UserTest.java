package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.User.STARTING_BALANCE;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private Deck deck;

    @BeforeEach
    public void setup() {
        user = new User();
        deck = new Deck();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, user.getHand().getSize());
        assertEquals(STARTING_BALANCE, user.getBalance());
        assertEquals(0, user.getBet());
        assertTrue(user.isTurn());
    }

    @Test
    public void testPlaceBet() {
        user.placeBet(10);
        assertEquals(10, user.getBet());
        assertEquals(STARTING_BALANCE - 10, user.getBalance());
    }


    @Test
    public void testDoubleDown() {
        user.placeBet(10);
        user.doubleDown(deck);
        assertEquals(20, user.getBet());
        assertEquals(STARTING_BALANCE - 20, user.getBalance());
        assertFalse(user.isTurn());
    }
}
