package blackjack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackjackGameTest {
    @Test
    void shouldStartRoundAndTakeBet() {
        BlackjackGame game = new BlackjackGame(100, fixedDeck(
                card(Rank.TEN), card(Rank.KING), card(Rank.FIVE), card(Rank.SIX),
                card(Rank.TWO), card(Rank.THREE), card(Rank.FOUR), card(Rank.SEVEN),
                card(Rank.EIGHT), card(Rank.NINE)
        ));

        game.startRound(20);

        assertEquals(GameState.PLAYER_TURN, game.getState());
        assertEquals(80, game.getPlayer().getBalance());
        assertEquals(20, game.getPlayer().getCurrentBet());
        assertEquals(15, game.getPlayer().getHand().getBestValue());
        assertEquals(16, game.getDealer().getHand().getBestValue());
    }

    @Test
    void shouldResolveRoundAfterStandAndPayPlayerWin() {
        BlackjackGame game = new BlackjackGame(100, fixedDeck(
                card(Rank.TEN), card(Rank.KING), card(Rank.QUEEN), card(Rank.SIX),
                card(Rank.TWO), card(Rank.THREE), card(Rank.FOUR), card(Rank.SEVEN),
                card(Rank.EIGHT), card(Rank.NINE)
        ));

        game.startRound(20);
        game.playerMove(Move.STAND);

        assertEquals(GameState.RESOLVED, game.getState());
        assertEquals(RoundResult.PLAYER_WIN, game.getLastResult());
        assertEquals(120, game.getPlayer().getBalance());
        assertEquals(0, game.getPlayer().getCurrentBet());
    }

    @Test
    void shouldResolveRoundWhenPlayerBustsAfterHit() {
        BlackjackGame game = new BlackjackGame(100, fixedDeck(
                card(Rank.KING), card(Rank.FIVE), card(Rank.EIGHT), card(Rank.NINE),
                card(Rank.FIVE), card(Rank.THREE), card(Rank.FOUR), card(Rank.SEVEN),
                card(Rank.TWO), card(Rank.SIX)
        ));

        game.startRound(10);
        game.playerMove(Move.HIT);

        assertEquals(GameState.RESOLVED, game.getState());
        assertEquals(RoundResult.PLAYER_BUST, game.getLastResult());
        assertEquals(90, game.getPlayer().getBalance());
        assertEquals(0, game.getPlayer().getCurrentBet());
    }

    @Test
    void shouldPayPlayerWhenDealerBusts() {
        BlackjackGame game = new BlackjackGame(100, fixedDeck(
                card(Rank.TEN),
                card(Rank.TEN),
                card(Rank.EIGHT),
                card(Rank.SIX),
                card(Rank.KING)
        ));

        game.startRound(10); // bet 10
        assertEquals(18, game.getPlayer().getHand().getBestValue());
        assertEquals(16, game.getDealer().getHand().getBestValue());

        game.playerMove(Move.STAND);

        assertEquals(GameState.RESOLVED, game.getState());
        assertEquals(RoundResult.PLAYER_WIN, game.getLastResult());
        assertEquals(110, game.getPlayer().getBalance());
        assertEquals(0, game.getPlayer().getCurrentBet());
    }

    private static Card card(Rank rank) {
        return new Card(Suit.SPADES, rank, rank.getValue());
    }

    private static Deck fixedDeck(Card... cards) {
        return new FixedDeck(cards);
    }

    private static class FixedDeck extends Deck {
        private final Deque<Card> cards = new ArrayDeque<>();

        FixedDeck(Card... cards) {
            for (Card card : cards) {
                this.cards.addLast(card);
            }
        }

        @Override
        public int getRemainingCardsCount() {
            return cards.size();
        }

        @Override
        public void shuffle() {
        }

        @Override
        public Card draw() {
            if (cards.isEmpty()) {
                throw new IllegalStateException("Empty Deck");
            }
            return cards.removeFirst();
        }
    }
}