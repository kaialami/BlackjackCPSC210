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
}
