package blackjack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
