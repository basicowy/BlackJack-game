package blackjack;

public class Card {
    private final Suit suit;
    private final Rank rank;
    private final int value;

    public Card(Suit suit, Rank rank, int value) {
        if (suit == null) {
            throw new IllegalArgumentException("Suit cannot be blank");
        }
        if (rank == null) {
            throw new IllegalArgumentException("Rank cannot be blank");
        }
        if (value < 1) {
            throw new IllegalArgumentException("Value must be positive");
        }

        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Suit getSuit() { return suit; }

    public Rank getRank() { return rank; }

    public int getValue() { return value; }

    @Override
    public String toString() {
        String displayValue = (value < 10 || rank == Rank.TEN) ? " (" + value + ")" : "";
        return rank + displayValue + " of " + suit;
    }
}