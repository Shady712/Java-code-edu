package game;

import java.util.Arrays;

public class RhombusBoard extends MNKBoard {

    private void build(int n) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(cells[i], 0, n - i - 1, Cell.B);
            Arrays.fill(cells[i], n + i, 2 * n - 1, Cell.B);
        }
        for (int i = n; i < 2 * n - 1; i++) {
            Arrays.fill(cells[i], 0, n - (2 * n - i) + 1, Cell.B);
            Arrays.fill(cells[i], 3 * n - 2 - i, 2 * n - 1, Cell.B);
        }
        cellsNum = n * n + (n - 1) * (n - 1);
    }
    public RhombusBoard(int n, int k) {
        super(2 * n - 1, 2 * n - 1, k);
        build(n);
    }

    public RhombusBoard(int n, int k, int extraNum) {
        super(2 * n - 1, 2 * n - 1, k, extraNum);
        build(n);
    }
}
