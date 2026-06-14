package blackjack;

public class Main {
    private static final int INITIAL_BALANCE = 100;

    public static void main(String[] args) {
        InputHandler input = new InputHandler();
        ConsoleUI ui = new ConsoleUI();
        BlackjackGame game = new BlackjackGame(INITIAL_BALANCE);
        boolean isRunning = true;

        ui.displayWelcome();

        while (isRunning && game.getPlayer().getBalance() > 0) {
            ui.displayMessage("");
            ui.displayBalance(game.getPlayer());

            int bet = askForBet(input, ui, game.getPlayer().getBalance());
            if (bet == 0) {
                isRunning = false;
                continue;
            }

            game.startRound(bet);
            ui.displayGameState(game.getPlayer(), game.getDealer(), game.getState());

            while (isRunning && game.getState() == GameState.PLAYER_TURN) {
                Move move = input.getMoveInput("Ruch [H] dobierz, [S] stoj, [D] podwoj, [Q] wyjdz: ");

                switch (move) {
                    case HIT -> game.playerHit();
                    case STAND -> game.playerStand();
                    case DOUBLE -> {
                        if (game.canPlayerDoubleDown()) {
                            game.playerDoubleDown();
                        } else {
                            ui.displayMessage("Nie mozesz teraz podwoic zakladu.");
                        }
                    }
                    case QUIT -> {
                        ui.displayMessage("Przerwano gre.");
                        isRunning = false;
                    }
                }

                if (isRunning && game.getState() == GameState.PLAYER_TURN) {
                    ui.displayGameState(game.getPlayer(), game.getDealer(), game.getState());
                }
            }

            if (isRunning && game.getState() == GameState.RESOLVED) {
                ui.displayHands(game.getPlayer(), game.getDealer(), false);
                ui.displayRoundResult(game.getLastResult(), game.getPlayer(), game.getDealer());
            }
        }

        if (game.getPlayer().getBalance() == 0) {
            ui.displayMessage("Koniec gry - portfel wyzerowany.");
        }
        ui.displayMessage("Do zobaczenia!");
    }

    private static int askForBet(InputHandler input, ConsoleUI ui, int balance) {
        while (true) {
            int bet = input.getIntInput("Podaj zaklad (0 = wyjscie): ");
            if (bet == 0) {
                return 0;
            }
            if (bet < 0) {
                ui.displayMessage("Zaklad nie moze byc ujemny.");
                continue;
            }
            if (bet > balance) {
                ui.displayMessage("Nie masz tylu srodkow.");
                continue;
            }

            return bet;
        }
    }
}
