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

        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        long acesCount = cards.stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .count();

        while (sum > 21 && acesCount > 0) {
            sum -= 10;
            acesCount--;
        }

        return sum;
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

    public boolean isBlackjack() {
        return cards.size() == 2 && getBestValue() == 21;
    }

    public boolean isBust() {
        return getBestValue() > 21;
    }

    public boolean isSoft() {
        int sum = 0;
        int acesCount = 0;

        for (Card card : cards) {
            sum += card.getValue();
            if (card.getRank() == Rank.ACE) {
                acesCount++;
            }
        }

        while (sum > 21 && acesCount > 0) {
            sum -= 10;
            acesCount--;
        }

        return acesCount > 0;
    }
}
