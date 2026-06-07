package blackjack;

public class Hand {
    private int score;

    public Hand() {
        this(0);
    }

    public Hand(int score) {
        setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Hand score cannot be negative");
        }

        this.score = score;
    }

    public void reset() {
        score = 0;
    }
}
