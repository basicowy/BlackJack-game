package blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void shouldAddCardToHand() {
        Hand hand = new Hand();
        // Zmieniono Stringi na Enumy
        Card card = new Card(Suit.HEARTS, Rank.TEN, 10);

        hand.addCard(card);

        assertEquals(1, hand.getCards().size());
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    void shouldCalculateSimpleHandValue() {
        Hand hand = new Hand();

        // Zmieniono Stringi na Enumy
        hand.addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE, 5));

        assertEquals(15, hand.getBestValue());
    }

    @Test
    void shouldClearHand() {
        Hand hand = new Hand();

        // Zmieniono Stringi na Enumy
        hand.addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE, 5));

        hand.clear();

        assertTrue(hand.getCards().isEmpty());
        assertEquals(0, hand.getBestValue());
    }
}