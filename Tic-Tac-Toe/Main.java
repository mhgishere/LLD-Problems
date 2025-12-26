import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int SIZE = readBoardSize(sc);
            Player player1 = readPlayer(sc);
            Player player2 = readPlayer(sc);

            Game game = new Game(SIZE, player1, player2);
            game.startGame(sc);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static int readBoardSize(Scanner sc) {
        int n = Integer.parseInt(sc.nextLine());
        if (n <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }
        return n;
    }

    private static Player readPlayer(Scanner sc) {
        Player player = new Player();
        String playerInformation = sc.nextLine();

        if (!(playerInformation.charAt(0) == 'X' || playerInformation.charAt(0) == 'O')) {
            throw new IllegalArgumentException("Player symbol must be X or O");
        }

        player.setPlayerInformation(playerInformation);
        return player;
    }
}
