package blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void shouldLimitBetToPlayerBalance() {
        Player player = new Player(100);

        player.placeBet(150);

        assertEquals(0, player.getBalance());
        assertEquals(100, player.getCurrentBet());
    }

    @Test
    void shouldNotDropBalanceBelowZeroWhenAddingBets() {
        Player player = new Player(100);

        player.placeBet(70);
        player.placeBet(70);

        assertEquals(0, player.getBalance());
        assertEquals(100, player.getCurrentBet());
    }

    @Test
    void shouldResetHandAndBetButKeepBalanceForNewRound() {
        Player player = new Player(100);
        player.placeBet(50);
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        player.getHand().addCard(new Card(Suit.SPADES, Rank.FIVE, 5));

        player.resetForNewRound();

        assertEquals(0, player.getHand().getCards().size());
        assertEquals(0, player.getCurrentBet());
        assertEquals(50, player.getBalance());
    }

    @Test
    void shouldNotAllowDoubleDownWhenBalanceIsLowerThanCurrentBet() {
        Player player = new Player(100);

        player.placeBet(60);

        assertFalse(player.canDoubleDown());
    }

    @Test
    void shouldAllowDoubleDownWhenBalanceCoversSecondSameBet() {
        Player player = new Player(100);

        player.placeBet(40);

        assertTrue(player.canDoubleDown());
    }
}