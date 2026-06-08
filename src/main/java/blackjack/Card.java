package blackjack;

public class Card {
    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank, int value) {
        if (suit == null || suit.isBlank()) {
            throw new IllegalArgumentException("Suit cannot be blank");
        }
        if (rank == null || rank.isBlank()) {
            throw new IllegalArgumentException("Rank cannot be blank");
        }
        if (value < 1) {
            throw new IllegalArgumentException("Value must be positive");
        }

        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() { return suit; }

    public String getRank() { return rank; }

    public int getValue() { return value; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
