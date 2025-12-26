public interface GameState {
    void handleMove(Game game, int r, int c);
    boolean isTerminal();
}