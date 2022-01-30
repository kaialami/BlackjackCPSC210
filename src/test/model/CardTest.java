package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card sixOfClubs;
    private Card kingOfDiamonds;
    private Card twoOfHearts;
    private Card aceOfSpades;

    @BeforeEach
    public void setup() {
        sixOfClubs = new Card(0, 6);
        kingOfDiamonds = new Card(1, 13);
        twoOfHearts = new Card(2, 2);
        aceOfSpades = new Card(3, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, sixOfClubs.getSuit());
        assertEquals(13, kingOfDiamonds.getValue());
    }
}