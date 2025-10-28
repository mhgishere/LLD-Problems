public class Player {
    private String name;
    private Piece piece;

    public void setPlayerInformation(String playerInformation) {
        this.name = playerInformation.substring(2);
        this.piece = playerInformation.charAt(0) == 'X' ? Piece.X : Piece.O;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }
}
