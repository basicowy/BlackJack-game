package blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void shouldAddCardToHand() {
        Hand hand = new Hand();
        Card card = new Card("HEARTS", "TEN", 10);

        hand.addCard(card);

        assertEquals(1, hand.getCards().size());
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    void shouldCalculateSimpleHandValue() {
        Hand hand = new Hand();

        hand.addCard(new Card("HEARTS", "TEN", 10));
        hand.addCard(new Card("SPADES", "FIVE", 5));

        assertEquals(15, hand.getBestValue());
    }

    @Test
    void shouldClearHand() {
        Hand hand = new Hand();

        hand.addCard(new Card("HEARTS", "TEN", 10));
        hand.addCard(new Card("SPADES", "FIVE", 5));

        hand.clear();

        assertTrue(hand.getCards().isEmpty());
        assertEquals(0, hand.getBestValue());
    }
}