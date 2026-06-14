package blackjack;

public class Player {
    private final Hand hand;
    private int balance;
    private int currentBet;

    public Player(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.hand = new Hand();
        this.balance = initialBalance;
        this.currentBet = 0;
    }

    public Hand getHand() {
        return hand;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void placeBet(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Bet amount cannot be negative");
        }

        int acceptedBet = Math.min(amount, balance);
        balance -= acceptedBet;
        currentBet += acceptedBet;
    }

    public boolean canDoubleDown() {
        return currentBet > 0 && balance >= currentBet;
    }

    public void winBet() {
        balance += currentBet * 2;
        currentBet = 0;
    }

    public void loseBet() {
        currentBet = 0;
    }

    public void pushBet() {
        balance += currentBet;
        currentBet = 0;
    }

    public void resetForNewRound() {
        hand.clear();
        currentBet = 0;
    }
}