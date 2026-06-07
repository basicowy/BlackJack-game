package blackjack;

public class Dealer extends Player {
    public Dealer() {
        super(0);
    }

    public boolean shouldHit() {
        return getHand().getScore() < 17;
    }
}
