public class DrawState implements GameState {

    @Override
    public void handleMove(Game game, int r, int c) {
        game.setDraw();
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}
