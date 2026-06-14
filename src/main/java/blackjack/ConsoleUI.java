package blackjack;

import java.io.PrintStream;
import java.util.List;
import java.util.StringJoiner;

public class ConsoleUI {
    private static final String HIDDEN_CARD = "[ukryta karta]";

    private final PrintStream out;

    public ConsoleUI() {
        this(System.out);
    }

    public ConsoleUI(PrintStream out) {
        if (out == null) {
            throw new IllegalArgumentException("Output stream cannot be null");
        }
        this.out = out;
    }

    public void displayWelcome() {
        out.println("Welcome to Blackjack!");
    }

    public void displayMessage(String msg) {
        out.println(msg);
    }

    public void displayBalance(Player player) {
        validatePlayer(player);

        out.println("Saldo gracza: " + player.getBalance());
        out.println("Aktualny zaklad: " + player.getCurrentBet());
    }

    public void displayHands(Player player, Dealer dealer, boolean hideDealerCard) {
        validatePlayer(player);
        validateDealer(dealer);

        out.println("Karty gracza: " + formatHand(player.getHand(), false));
        out.println("Punkty gracza: " + player.getHand().getBestValue());
        out.println("Karty krupiera: " + formatHand(dealer.getHand(), hideDealerCard));
        out.println("Punkty krupiera: " + (hideDealerCard ? "?" : dealer.getHand().getBestValue()));
    }

    public void displayRoundResult(RoundResult result, Player player, Dealer dealer) {
        if (result == null) {
            throw new IllegalArgumentException("Round result cannot be null");
        }
        validatePlayer(player);
        validateDealer(dealer);

        out.println("Wynik rundy: " + getRoundResultMessage(result));
        out.println("Punkty gracza: " + player.getHand().getBestValue());
        out.println("Punkty krupiera: " + dealer.getHand().getBestValue());
        displayBalance(player);
    }

    public void displayGameState(Player player, Dealer dealer, GameState state) {
        if (state == null) {
            throw new IllegalArgumentException("Game state cannot be null");
        }

        displayBalance(player);
        displayHands(player, dealer, state == GameState.PLAYER_TURN);
    }

    private String formatHand(Hand hand, boolean hideOneCard) {
        List<Card> cards = hand.getCards();
        if (cards.isEmpty()) {
            return "(brak kart)";
        }

        StringJoiner joiner = new StringJoiner(", ");
        int hiddenCardIndex = hideOneCard && cards.size() > 1 ? 1 : 0;

        for (int i = 0; i < cards.size(); i++) {
            if (hideOneCard && i == hiddenCardIndex) {
                joiner.add(HIDDEN_CARD);
            } else {
                joiner.add(formatCard(cards.get(i)));
            }
        }

        return joiner.toString();
    }

    private String formatCard(Card card) {
        return card.toString();
    }

    private String getRoundResultMessage(RoundResult result) {
        return switch (result) {
            case PLAYER_WIN -> "Wygrana gracza";
            case DEALER_WIN -> "Wygrana krupiera";
            case BLACKJACK_WIN -> "Blackjack - wygrana gracza";
            case PUSH -> "Remis";
            case PLAYER_BUST -> "Gracz przekroczyl 21";
        };
    }

    private void validatePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
    }

    private void validateDealer(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("Dealer cannot be null");
        }
    }
}

