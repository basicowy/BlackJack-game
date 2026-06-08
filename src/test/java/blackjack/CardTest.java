package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void shouldCreateCardAndReturnCorrectValues() {
        Card card = new Card(Suit.HEARTS, Rank.ACE, 11);

        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(Rank.ACE, card.getRank());
        assertEquals(11, card.getValue());
    }

    @Test
    void shouldReturnCorrectToStringFormat() {
        Card card = new Card(Suit.SPADES, Rank.QUEEN, 10);

        assertEquals("QUEEN of SPADES", card.toString());
    }

    @Test
    void shouldThrowExceptionWhenSuitIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Card(null, Rank.TEN, 10));
    }

    @Test
    void shouldThrowExceptionWhenRankIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Card(Suit.CLUBS, null, 10));
    }

    @Test
    void shouldThrowExceptionWhenValueIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Card(Suit.DIAMONDS, Rank.TWO, 0));
        assertThrows(IllegalArgumentException.class, () -> new Card(Suit.DIAMONDS, Rank.TWO, -5));
    }
}