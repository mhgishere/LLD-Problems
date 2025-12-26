public class Player {
    private String name;
    private Symbol symbol;

    public void setPlayerInformation(String playerInformation) {
        this.name = playerInformation.substring(2);
        this.symbol = playerInformation.charAt(0) == 'X' ? Symbol.X : Symbol.O;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
