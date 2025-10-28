import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int SIZE = Integer.parseInt(sc.nextLine());

        Player player1 = new Player();
        Player player2 = new Player();

        String player1Info = sc.nextLine();
        player1.setPlayerInformation(player1Info);

        String player2Info = sc.nextLine();
        player2.setPlayerInformation(player2Info);

        Game game = new Game(SIZE, player1, player2);
        game.startGame(sc);

        sc.close();
    }
}
