public class Grid {
    private char[][] grid;

    public Grid() {
        grid = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid[i][j] = '-';
            }
        } 
    }

    public void displayGrid() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean performMove(int i, int j, Piece piece) {
        boolean isValidMove = validateMove(i, j);
        if(isValidMove) {
            grid[i][j] = piece == Piece.X ? 'X' : 'O';
        }
        return isValidMove;
    }

    private boolean validateMove(int i, int j) {
        return grid[i][j] == '-';
    }

    public boolean isGameOver() {
        return checkRows() || checkColumns() || checkDiagnols();
    }

    private boolean checkRows() {
        for(int i = 0; i < 3; i++) {
            if(grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] && grid[i][0] != '-') {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for(int i = 0; i < 3; i++) {
            if(grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] && grid[0][i] != '-') {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagnols() {
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] != '-') {
            return true;
        }
        if(grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2] && grid[2][0] != '-') {
            return true;
        }
        return false;
    }

    
}
