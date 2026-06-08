package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();
    private Integer manualScore;

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        cards.add(card);
        manualScore = null;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void clear() {
        cards.clear();
        manualScore = null;
    }

    public int getBestValue() {
        if (manualScore != null) {
            return manualScore;
        }

        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public int getScore() {
        return getBestValue();
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        manualScore = score;
    }
}
