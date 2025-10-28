import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private Piece piece;
    public static Map<Piece, String> pieceNameMapping = new HashMap<>();

    public void setPlayerInformation(String playerInformation) {
        this.name = playerInformation.substring(2);
        this.piece = playerInformation.charAt(0) == 'X' ? Piece.X : Piece.O;
        pieceNameMapping.put(piece, name);
    }
}
