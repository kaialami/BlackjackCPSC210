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
    public void testConstructorNoParam() {
        assertEquals(STARTING_BALANCE, user.getBalance());
        constructorRemainingFieldsTesting(user);
    }

    @Test
    public void testConstructorBalanceParam() {
        User userManualBal = new User(500);
        assertEquals(500, userManualBal.getBalance());
        constructorRemainingFieldsTesting(userManualBal);
    }

    @Test
    public void testPlaceBet() {
        user.placeBet(10);
        assertEquals(10, user.getBet());
        assertEquals(STARTING_BALANCE - 10, user.getBalance());
    }

    @Test
    public void testHitNoBust() {
        Hand hand = user.hit(deck);
        assertEquals(1, hand.getSize());
        assertTrue(user.isDoubleDown());
    }

    @Test
    public void testHitBust() {
        while (user.getScore() != -1) {
            user.hit(deck);
        }
        assertEquals(-1, user.getScore());
        assertFalse(user.isTurn());
        assertTrue(user.isDoubleDown());
    }

    @Test
    public void testHitBustLotsOfTimes() {
        for (int i = 0; i < 300; i++) {
            testHitBust();
        }
    }

    @Test
    public void testDoubleDown() {
        user.placeBet(10);
        user.doubleDown(deck);
        assertEquals(20, user.getBet());
        assertEquals(STARTING_BALANCE - 20, user.getBalance());
        assertFalse(user.isTurn());
        assertTrue(user.isDoubleDown());
    }

    @Test
    public void testPayout() {
        user.placeBet(50);
        assertEquals(50, user.getBalance());
        assertEquals(50, user.getBet());

        user.payout(100);
        assertEquals(150, user.getBalance());
        assertEquals(0, user.getBet());
    }

    @Test
    public void testSetDoubleDown() {
        user.setDoubleDown(true);
        assertTrue(user.isDoubleDown());
        user.setDoubleDown(false);
        assertFalse(user.isDoubleDown());
    }

    // tests the unchanging fields between the two constructors
    public void constructorRemainingFieldsTesting(User user) {
        assertEquals(0, user.getHand().getSize());
        assertEquals(0, user.getScore());
        assertFalse(user.isTurn());
        assertEquals(0, user.getBet());
    }
}
