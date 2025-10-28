import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Grid {
    private char[][] grid;
    int n;
    Map<Piece, List<Integer>> rowCount = new HashMap<>();
    Map<Piece, List<Integer>> columnCount = new HashMap<>();
    Map<Piece, Integer> diagnol1 = new HashMap<>();
    Map<Piece, Integer> diagnol2 = new HashMap<>();

    public Grid(int n) {
        this.n = n;
        grid = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void displayGrid() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean performMove(int i, int j, Piece piece) {
        if(!validateMove(i, j)) {
            throw new IllegalArgumentException("Invalid Move");
        }
        
        grid[i][j] = piece == Piece.X ? 'X' : 'O';

        rowCount.putIfAbsent(piece, new ArrayList<>(Collections.nCopies(n, 0)));
        columnCount.putIfAbsent(piece, new ArrayList<>(Collections.nCopies(n, 0)));
        diagnol1.putIfAbsent(piece, 0);
        diagnol2.putIfAbsent(piece, 0);

        Integer currentRowFreq = rowCount.get(piece).get(i);
        rowCount.get(piece).set(i, currentRowFreq + 1);

        Integer currentColumnFreq = columnCount.get(piece).get(j);
        columnCount.get(piece).set(j, currentColumnFreq + 1);

        if(i == j) {
            diagnol1.put(piece, diagnol1.get(piece) + 1);
        }

        if(i+j == n-1) {
            diagnol2.put(piece, diagnol2.get(piece) + 1);
        }

        return isGameOver(i, j, piece);
    }

    private boolean validateMove(int i, int j) {
        return (i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == '-');
    }

    public boolean isGameOver(int i, int j, Piece piece) {
        return rowCount.get(piece).get(i) == n || columnCount.get(piece).get(j) == n || 
            diagnol1.get(piece) == n || diagnol2.get(piece) == n;
    }
}
