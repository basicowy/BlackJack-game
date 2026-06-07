package blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {
    @Test
    void shouldHitWhenScoreIs16() {
        Dealer dealer = new Dealer();
        dealer.getHand().setScore(16);

        assertTrue(dealer.shouldHit());
    }

    @Test
    void shouldStandWhenScoreIs17() {
        Dealer dealer = new Dealer();
        dealer.getHand().setScore(17);

        assertFalse(dealer.shouldHit());
    }
}
