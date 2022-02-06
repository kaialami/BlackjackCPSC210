package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setup() {
        deck = new Deck();
    }

    @Test
    public void testConstructor() {
        assertTrue(deck.getFullDeck() == deck.getActiveDeck());
    }

    @Test
    public void testGenerateFullDeck() {
        List<Card> fullDeck = deck.getFullDeck();
        Card card1 = fullDeck.get(0);
        Card card2 = fullDeck.get(1);
        Card card3 = fullDeck.get(51);

        assertEquals(52, fullDeck.size());
        assertEquals(0, card1.getSuit());
        assertEquals(1, card1.getValue());
        assertEquals(0, card2.getSuit());
        assertEquals(2, card2.getValue());
        assertEquals(13, card3.getValue());
        assertEquals(3, card3.getSuit());
    }

    @Test
    public void testRemoveCard() {
        deck.removeCard();
        assertEquals(51, deck.getActiveDeck().size());
        deck.removeCard();
        assertEquals(50, deck.getActiveDeck().size());
    }
}
