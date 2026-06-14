package blackjack;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleUITest {
    private final ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();

    @Test
    void shouldHideOneDealerCardDuringPlayerTurn() {
        Player player = new Player(100);
        Dealer dealer = new Dealer();
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        player.getHand().addCard(new Card(Suit.CLUBS, Rank.FIVE, 5));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.KING, 10));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE, 11));
        ConsoleUI ui = new ConsoleUI(output());

        ui.displayHands(player, dealer, true);

        String text = capturedText();
        assertTrue(text.contains("Karty gracza: TEN (10) of HEARTS, FIVE (5) of CLUBS"));
        assertTrue(text.contains("Punkty gracza: 15"));
        assertTrue(text.contains("Karty krupiera: KING of SPADES, [ukryta karta]"));
        assertTrue(text.contains("Punkty krupiera: ?"));
        assertFalse(text.contains("ACE of HEARTS"));
    }

    @Test
    void shouldRevealDealerCardsWhenHiddenCardOptionIsOff() {
        Player player = new Player(100);
        Dealer dealer = new Dealer();
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.NINE, 9));
        player.getHand().addCard(new Card(Suit.CLUBS, Rank.SEVEN, 7));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.KING, 10));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.ACE, 11));
        ConsoleUI ui = new ConsoleUI(output());

        ui.displayHands(player, dealer, false);

        String text = capturedText();
        assertTrue(text.contains("Karty krupiera: KING of SPADES, ACE of HEARTS"));
        assertTrue(text.contains("Punkty krupiera: 21"));
        assertFalse(text.contains("[ukryta karta]"));
    }

    @Test
    void shouldDisplayRoundResultAndBalance() {
        Player player = new Player(100);
        Dealer dealer = new Dealer();
        player.placeBet(25);
        player.getHand().addCard(new Card(Suit.HEARTS, Rank.TEN, 10));
        player.getHand().addCard(new Card(Suit.CLUBS, Rank.EIGHT, 8));
        dealer.getHand().addCard(new Card(Suit.SPADES, Rank.KING, 10));
        dealer.getHand().addCard(new Card(Suit.HEARTS, Rank.SIX, 6));
        ConsoleUI ui = new ConsoleUI(output());

        ui.displayRoundResult(RoundResult.PLAYER_WIN, player, dealer);

        String text = capturedText();
        assertTrue(text.contains("Wynik rundy: Wygrana gracza"));
        assertTrue(text.contains("Punkty gracza: 18"));
        assertTrue(text.contains("Punkty krupiera: 16"));
        assertTrue(text.contains("Saldo gracza: 75"));
        assertTrue(text.contains("Aktualny zaklad: 25"));
    }

    private PrintStream output() {
        capturedOutput.reset();
        return new PrintStream(capturedOutput, true, StandardCharsets.UTF_8);
    }

    private String capturedText() {
        return new String(capturedOutput.toByteArray(), StandardCharsets.UTF_8);
    }
}
