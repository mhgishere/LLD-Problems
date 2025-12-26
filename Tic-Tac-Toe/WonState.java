

public class WonState implements GameState {
    private final Player winner;

    public WonState(Player winner) {
        this.winner = winner;
    }

    @Override
    public void handleMove(Game game, int r, int c) {
        game.setWinner(winner);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
