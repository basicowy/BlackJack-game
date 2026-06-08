package blackjack;

import java.util.*;

public class Deck {
    private final ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank, rank.getValue()));
            }
        }
    }

    public int getRemainingCardsCount() {
        return deck.size();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (getRemainingCardsCount() == 0) {
            throw new IllegalStateException("Empty Deck");
        }
        return deck.removeLast();
    }

}
