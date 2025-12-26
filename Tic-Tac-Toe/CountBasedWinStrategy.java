import java.util.HashMap;
import java.util.Map;

public class CountBasedWinStrategy implements WinStrategy {
    private final int n;
    private final Map<Symbol, int[]> rows = new HashMap<>();
    private final Map<Symbol, int[]> cols = new HashMap<>();
    private final Map<Symbol, Integer> diag1 = new HashMap<>();
    private final Map<Symbol, Integer> diag2 = new HashMap<>();

    public CountBasedWinStrategy(int n) {
        this.n = n;
    }

    @Override
    public boolean checkWin(int r, int c, Symbol s) {
        rows.putIfAbsent(s, new int[n]);
        cols.putIfAbsent(s, new int[n]);
        diag1.putIfAbsent(s, 0);
        diag2.putIfAbsent(s, 0);

        if (++rows.get(s)[r] == n) return true;
        if (++cols.get(s)[c] == n) return true;

        if (r == c && diag1.put(s, diag1.get(s) + 1) == n - 1) return true;
        if (r + c == n - 1 && diag2.put(s, diag2.get(s) + 1) == n - 1) return true;

        return false;
    }
}