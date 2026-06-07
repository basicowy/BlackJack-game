package blackjack;

public class Rules {
    public static RoundResult determineWinner(int playerScore, int dealerScore, boolean isPlayerBust, boolean isDealerBust) {
        if (isPlayerBust) return RoundResult.PLAYER_BUST;

        if (isDealerBust) return RoundResult.PLAYER_WIN;

        if (playerScore == 21) return RoundResult.BLACKJACK_WIN;

        if (playerScore == dealerScore) return RoundResult.PUSH;

        if (playerScore <= 21 && playerScore > dealerScore) return RoundResult.PLAYER_WIN;
        else return RoundResult.DEALER_WIN;
    }
}
