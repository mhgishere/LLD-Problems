public class InProgressState implements GameState {
    @Override
    public void handleMove(Game game, int r, int c) {
        Player player = game.getCurrentPlayer();
        game.getBoard().placeMove(r, c, player.getSymbol());
        game.incrementMoves();

        if (game.getWinStrategy().checkWin(r, c, player.getSymbol())) {
            game.setState(new WonState(player));
            game.getState().handleMove(game, r, c);
            return;
        }

        if (game.isBoardFull()) {
            game.setState(new DrawState());
            game.getState().handleMove(game, r, c);
            return;
        }
        
        game.switchTurn();
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
}
