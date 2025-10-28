import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player1 = new Player();
        Player player2 = new Player();

        String player1Info = sc.nextLine();
        player1.setPlayerInformation(player1Info);

        String player2Info = sc.nextLine();
        player2.setPlayerInformation(player2Info);

        Grid currentGrid = new Grid();
        Piece[] pieces = {Piece.X, Piece.O};

        currentGrid.displayGrid();

        int numberOfMoves = 0;
        while(numberOfMoves < 9) {
            String move = sc.nextLine();
            if(move.equals("exit")) {
                break;
            }

            boolean isMoveSuccessful = currentGrid.performMove(move.charAt(0) - '1', move.charAt(2) - '1', pieces[numberOfMoves % 2]);
            if(isMoveSuccessful == false) {
                System.out.println("Invalid Move");
                continue;
            }

            currentGrid.displayGrid();

            if(currentGrid.isGameOver()) {
                String winnerName = Player.pieceNameMapping.get(pieces[numberOfMoves % 2]);
                System.out.println(winnerName + " won the game");
                break;
            }

            numberOfMoves++;
        }

        if(numberOfMoves == 9) {
            System.out.println("Game Over");
        }

        sc.close();
    }
}
