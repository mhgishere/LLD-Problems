public class Board {
    private final Symbol[][] board;
    private final int size;

    public Board(int size) {
        this.size = size;
        board = new Symbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(int r, int c) {
        return r >= 0 && r < size &&
               c >= 0 && c < size &&
               board[r][c] == Symbol.EMPTY;
    }

    public void placeMove(int r, int c, Symbol symbol) {
        if (!isValidMove(r, c)) {
            throw new IllegalArgumentException("Invalid Move");
        }
        board[r][c] = symbol;
    }

    public void display() {
        for (Symbol[] row : board) {
            for (Symbol s : row) {
                System.out.print(s.getChar() + " ");
            }
            System.out.println();
        }
    }
}
