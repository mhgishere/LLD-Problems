import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private Player player1, player2;
    private Grid board;
    private int gridSize, numberOfMoves;

    Game(int n, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Grid(n);
        numberOfMoves = 0;
        gridSize = n;
    }

    public void startGame(Scanner sc) {
        List<Player> players = new ArrayList<>(Arrays.asList(player1, player2));
        if(player1.getPiece() == Piece.O) {
            Collections.swap(players, 0, 1);    
        }

        board.displayGrid();

        while(numberOfMoves < gridSize * gridSize) {
            String move = sc.nextLine();
            if(move.equals("exit")) {
                break;
            }

            try {
                boolean isGameOver = board.performMove(move.charAt(0) - '1', move.charAt(2) - '1', players.get(numberOfMoves % 2).getPiece());
                board.displayGrid();
                if(isGameOver) {
                    String winnerName = players.get(numberOfMoves % 2).getName();
                    System.out.println(winnerName + " won the game");
                    break;
                }

            } catch(Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            numberOfMoves++;
        }

        if(numberOfMoves == gridSize * gridSize) {
            System.out.println("Game Over");
        }
    }
}
