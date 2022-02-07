package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    private Hand hand;
    private Deck deck;

    @BeforeEach
    public void setup() {
        hand = new Hand();
        deck = new Deck();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, hand.getCards().size());
    }

    @Test
    public void testAddCard() {
        hand.addCard(deck);
        assertEquals(1, hand.getSize());
        assertEquals(51, deck.getActiveDeck().size());
    }

    @Test
    public void testAddSpecificCardsTestMethods() {
        hand.addThree();
        assertEquals(3, hand.getCards().get(0).getValue());

        hand.addQueen();
        assertEquals(12, hand.getCards().get(1).getValue());

        hand.addAce();
        assertEquals(1, hand.getCards().get(2).getValue());
    }

    @Test
    public void testEvaluateOneCard() {
        hand.addThree();
        assertEquals(3, hand.evaluate());
    }

    @Test
    public void testEvaluateTwoCards() {
        hand.addThree();
        hand.addQueen();
        assertEquals(13, hand.evaluate());
    }

    @Test
    public void testEvaluateOneAce() {
        hand.addAce();
        assertEquals(11, hand.evaluate());
    }

    @Test
    public void testEvaluateTwoAces() {
        hand.addAce();
        hand.addAce();
        assertEquals(12, hand.evaluate());
    }

    @Test
    public void testEvaluateUseAceAsOne() {
        hand.addQueen();
        hand.addThree();
        hand.addAce();
        assertEquals(14, hand.evaluate());
    }

    @Test
    public void testEvaluateBlackJack() {
        hand.addAce();
        hand.addQueen();
        assertEquals(21, hand.evaluate());
    }

    @Test
    public void testEvaluateBust() {
        hand.addQueen();
        hand.addQueen();
        hand.addThree();
        assertEquals(-1, hand.evaluate());
    }

    @Test
    public void testEvaluateAcesHighToLow() {
        hand.addAce();
        assertEquals(11, hand.evaluate());
        hand.addAce();
        assertEquals(12, hand.evaluate());
        hand.addQueen();
        assertEquals(12, hand.evaluate());
    }

    @Test
    public void testHandleAceBehaviourNone() {
        int[] results = hand.handleAceBehaviour(20, 0);
        assertEquals(20, results[0]);
    }

    @Test
    public void testHandleAceBehaviourOne() {
        int[] results = hand.handleAceBehaviour(31, 1);
        assertEquals(21, results[0]);
    }

    @Test
    public void testHandleAceBehaviourTwo() {
        int[] results = hand.handleAceBehaviour(40, 2);
        assertEquals(20, results[0]);
    }
}
