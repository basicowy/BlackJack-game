package blackjack;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class DeckTest {
    @Test
    void shouldHave52CardsWhenCreated() {
        Deck deck = new Deck();
        assertEquals(52, deck.getRemainingCardsCount());
    }

    @Test
    void shouldDecreaseSizeWhenCardIsDrawn() {
        Deck deck = new Deck();
        Card drawnCard = deck.draw();
        assertNotNull(drawnCard);
        assertEquals(51, deck.getRemainingCardsCount());
    }

    @Test
    void shouldThrowExceptionWhenDrawingFromEmptyDeck() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThrows(IllegalStateException.class, () -> deck.draw());
    }

    @Test
    void shouldHaveUniqueCards() {
        Deck deck = new Deck();
        Set<String> uniqueCards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            uniqueCards.add(card.toString());
        }

        assertEquals(52, uniqueCards.size());
    }

}
