package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        assertEquals(52, deck.getActiveDeck().size());
    }

    @Test
    public void testGenerateFullDeck() {
        List<Card> fullDeck = deck.getActiveDeck();
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

    @Test
    public void testShuffle() {
        deck.removeCard();
        deck.shuffle();
        assertEquals(52, deck.getActiveDeck().size());
    }

    @Test
    public void testReplaceDeckWith() {
        List<Card> newCards = new ArrayList<>();
        newCards.add(new Card(0,0));
        deck.replaceDeckWith(newCards);
        assertEquals(1, deck.getActiveDeck().size());
    }

    @Test
    public void testDealOneCard() {
        User user = new User();
        deck.dealOneCard(user);
        assertEquals(51, deck.getActiveDeck().size());
        assertEquals(1, user.getHand().getSize());
    }
}
