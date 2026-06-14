package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void shouldAddCardToHand() {
        Card card = new Card(Suit.HEARTS, Rank.TEN, 10);

        hand.addCard(card);

        assertEquals(1, hand.getCards().size());
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    void shouldCalculateSimpleHandValue() {
        hand.addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE, 5));

        assertEquals(15, hand.getBestValue());
    }

    @Test
    void shouldClearHand() {
        hand.addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE, 5));

        hand.clear();

        assertTrue(hand.getCards().isEmpty());
        assertEquals(0, hand.getBestValue());
    }

    @Test
    void shouldScoreBlackjackWithAceAndKing() {
        hand.addCard(new Card(Suit.SPADES, Rank.ACE, Rank.ACE.getValue()));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING, Rank.KING.getValue()));

        assertEquals(21, hand.getBestValue());
        assertTrue(hand.isBlackjack());
        assertFalse(hand.isBust());
        assertTrue(hand.isSoft());
    }

    @Test
    void shouldReduceAceValueWhenOver21() {
        // A + 9 + 5 = 15
        hand.addCard(new Card(Suit.SPADES, Rank.ACE, Rank.ACE.getValue()));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE, Rank.NINE.getValue()));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE, Rank.FIVE.getValue()));

        assertEquals(15, hand.getBestValue());
        assertFalse(hand.isBlackjack());
        assertFalse(hand.isBust());
        assertFalse(hand.isSoft());
    }

    @Test
    void shouldReturnBustWhenOver21() {
        hand.addCard(new Card(Suit.SPADES, Rank.TEN, Rank.TEN.getValue()));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE, Rank.NINE.getValue()));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE, Rank.FIVE.getValue()));

        assertEquals(24, hand.getBestValue());
        assertTrue(hand.isBust());
        assertFalse(hand.isBlackjack());
    }
}