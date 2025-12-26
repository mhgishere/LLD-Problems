import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Player winner;
    private Board board;
    private WinStrategy winStrategy;
    private GameState state;
    private int turn = 0, boardSize, movesPlayed;
    private boolean draw;

    Game(int n, Player player1, Player player2) {
        this.players = new ArrayList<>(List.of(player1, player2));
        this.board = new Board(n);
        this.winStrategy = new CountBasedWinStrategy(n);
        this.state = new InProgressState();
        this.boardSize = n;
        this.movesPlayed = 0;
        this.draw = false;
    }

    public void playMove(int r, int c) {
        state.handleMove(this, r, c);
        board.display();

        if (state.isTerminal()) {
            if (hasWinner()) {
                System.out.println("Game Over! Winner: " + winner.getName());
            } else if (isDraw()) {
                System.out.println("Game Over! It's a draw.");
            }
        }
    }

    void incrementMoves() {
        movesPlayed++;
    }

    boolean isBoardFull() {
        return movesPlayed == boardSize * boardSize;
    }

    Player getCurrentPlayer() {
        return players.get(turn);
    }

    void switchTurn() {
        turn = 1 - turn;
    }

    Board getBoard() {
        return board;
    }

    WinStrategy getWinStrategy() {
        return winStrategy;
    }

    GameState getState() {
        return state;
    }

    void setState(GameState state) {
        this.state = state;
    }

    boolean isGameOver() {
        return state.isTerminal();
    }

    boolean hasWinner() {
        return winner != null;
    }
    
    Player getWinner() {
        return winner;
    }

    void setWinner(Player player) {
        this.winner = player;
    }
    
    boolean isDraw() {
        return draw;
    }
    
    public void setDraw() {
        this.draw = true;
    }

    public void startGame(Scanner sc) {
        if(players.get(0).getSymbol() == Symbol.O) {
            Collections.swap(players, 0, 1);    
        }

        board.display();

        while(!isGameOver()) {
            String move = sc.nextLine();
            if(move.equals("exit")) {
                break;
            }

            try {
                playMove(move.charAt(0) - '1', move.charAt(2) - '1');
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
