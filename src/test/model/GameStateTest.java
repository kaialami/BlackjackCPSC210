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
        assertEquals(-1, gs.getUserBalance());
    }

    @Test
    public void testSetUserOnce() {
        gs.setUserBalance(100);
        assertEquals(100, gs.getUserBalance());
    }

    @Test
    public void testSetUserMultipleTimes() {
        gs.setUserBalance(100);
        assertEquals(100, gs.getUserBalance());
        gs.setUserBalance(500);
        assertEquals(500, gs.getUserBalance());
    }
}
