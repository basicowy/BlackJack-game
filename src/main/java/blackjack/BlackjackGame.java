package blackjack;

public class BlackjackGame {
    private static final int BLACKJACK_SCORE = 21;
    private static final int MIN_CARDS_BEFORE_ROUND = 10;

    private final Player player;
    private final Dealer dealer;
    private Deck deck;
    private GameState state;
    private RoundResult lastResult;

    public BlackjackGame(int initialBalance) {
        this(initialBalance, newShuffledDeck());
    }

    BlackjackGame(int initialBalance, Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck cannot be null");
        }
        player = new Player(initialBalance);
        dealer = new Dealer();
        this.deck = deck;
        state = GameState.BETTING;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameState getState() {
        return state;
    }

    public RoundResult getLastResult() {
        return lastResult;
    }

    public void startRound(int bet) {
        if (bet <= 0) {
            throw new IllegalArgumentException("Zaklad musi byc wiekszy od 0");
        }
        if (bet > player.getBalance()) {
            throw new IllegalArgumentException("Zaklad nie moze przekraczac salda gracza");
        }

        prepareDeckForRound();
        player.resetForNewRound();
        dealer.resetForNewRound();
        player.placeBet(bet);
        lastResult = null;

        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());

        state = GameState.PLAYER_TURN;
        if (player.getHand().getBestValue() == BLACKJACK_SCORE) {
            resolveRound();
        }
    }

    public void playerHit() {
        ensurePlayerTurn();

        player.getHand().addCard(drawCard());
        if (isBust(player)) {
            resolveRound();
        }
    }

    public void playerStand() {
        ensurePlayerTurn();
        playDealerTurn();
        resolveRound();
    }

    public void playerDoubleDown() {
        ensurePlayerTurn();
        if (!canPlayerDoubleDown()) {
            throw new IllegalStateException("Nie mozna teraz podwoic zakladu");
        }

        player.placeBet(player.getCurrentBet());
        player.getHand().addCard(drawCard());

        if (isBust(player)) {
            resolveRound();
        } else {
            playDealerTurn();
            resolveRound();
        }
    }

    public boolean canPlayerDoubleDown() {
        return state == GameState.PLAYER_TURN
                && player.canDoubleDown()
                && player.getHand().getCards().size() == 2;
    }

    private void playDealerTurn() {
        state = GameState.DEALER_TURN;
        while (dealer.shouldHit()) {
            dealer.getHand().addCard(drawCard());
        }
    }

    private void resolveRound() {
        RoundResult result = Rules.determineWinner(
                player.getHand().getBestValue(),
                dealer.getHand().getBestValue(),
                isBust(player),
                isBust(dealer)
        );

        switch (result) {
            case PLAYER_WIN, BLACKJACK_WIN -> player.winBet();
            case DEALER_WIN, PLAYER_BUST -> player.loseBet();
            case PUSH -> player.pushBet();
        }

        lastResult = result;
        state = GameState.RESOLVED;
    }

    private boolean isBust(Player roundPlayer) {
        return roundPlayer.getHand().getBestValue() > BLACKJACK_SCORE;
    }

    private void prepareDeckForRound() {
        if (deck.getRemainingCardsCount() < MIN_CARDS_BEFORE_ROUND) {
            deck = newShuffledDeck();
        }
    }

    private Card drawCard() {
        if (deck.getRemainingCardsCount() == 0) {
            deck = newShuffledDeck();
        }
        return deck.draw();
    }

    private static Deck newShuffledDeck() {
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();
        return shuffledDeck;
    }

    private void ensurePlayerTurn() {
        if (state != GameState.PLAYER_TURN) {
            throw new IllegalStateException("To nie jest tura gracza");
        }
    }
}