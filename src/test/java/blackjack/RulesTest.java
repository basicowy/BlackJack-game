package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    @Test
    void shouldReturnPlayerBust() {
        RoundResult result = Rules.determineWinner(25, 15, true, false);
        assertEquals(RoundResult.PLAYER_BUST, result);
    }

    @Test
    void shouldReturnPlayerWin() {
        RoundResult result = Rules.determineWinner(20, 19, false, false);
        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    void shouldReturnPlayerWinWhenDealerBust() {
        RoundResult result = Rules.determineWinner(20, 25, false, true);
        assertEquals(RoundResult.PLAYER_WIN, result);
    }

    @Test
    void shouldReturnDealerWin() {
        RoundResult result = Rules.determineWinner(19, 20, false, false);
        assertEquals(RoundResult.DEALER_WIN, result);
    }

    @Test
    void shouldReturnBlackjackWin() {
        RoundResult result = Rules.determineWinner(21, 15, false, false);
        assertEquals(RoundResult.BLACKJACK_WIN, result);
    }

    @Test
    void shouldReturnPush() {
        RoundResult result = Rules.determineWinner(20, 20, false, false);
        assertEquals(RoundResult.PUSH, result);
    }
}
