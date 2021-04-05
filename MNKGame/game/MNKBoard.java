package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.B, ' '
    );
    private boolean enableExtraMoves = false;
    private int extraNum;
    protected final Cell[][] cells;
    private Cell turn;
    protected final int m, n, k;
    protected int cellsNum;
    private final int digits;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        cellsNum = n * m;
        digits = Math.max(countDigits(m - 1), countDigits(n - 1));
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    public MNKBoard(int m, int n, int k, int extraNum) {
        this(m, n, k);
        this.extraNum = extraNum;
        enableExtraMoves = true;
    }

    private int countDigits(int x) {
        int ans = 0;
        if (x == 0) {
            return 1;
        }
        while (x > 0) {
            ans++;
            x /= 10;
        }
        return ans;
    }

    @Override
    public Position getPosition() {
        return new AntiCheat(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private int countCells(Move move, int deltaX, int deltaY) {
        int ans = 0, x = move.getRow(), y = move.getColumn();
        while (x >= 0 && y >= 0 && x < n && y < m && cells[x][y] == move.getValue()) {
            ans++;
            x += deltaX;
            y += deltaY;
        }
        return ans;
    }

    private boolean check(Move move, int number) {
        return countCells(move, -1, 0) + countCells(move, 1, 0) - 1 >= number
                || countCells(move, 0, -1) + countCells(move, 0, 1) - 1 >= number
                || countCells(move, -1, -1) + countCells(move, 1, 1) - 1 >= number
                || countCells(move, 1, -1) + countCells(move, -1, 1) - 1 >= number;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        cellsNum--;

        if (check(move, k)) {
            return Result.WIN;
        }

        if (cellsNum == 0) {
            return Result.DRAW;
        }

        if (enableExtraMoves && check(move, extraNum)) {
            return Result.EXTRA_MOVE;
        }

        if (turn == Cell.X) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return move != null
                && 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    private void addNum(int x, StringBuilder sb) {
        for (int i = 0; i < digits - countDigits(x); i++) {
            sb.append(' ');
        }
        sb.append(x);
    }

    private void addChar(char x, StringBuilder sb) {
        for (int i = 0; i < digits - 1; i++) {
            sb.append(' ');
        }
        sb.append(x);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits + 1; i++) {
            sb.append(' ');
        }
        for (int i = 0; i < m; i++) {
            addNum(i, sb);
            sb.append(' ');
        }
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            addNum(i, sb);
            sb.append(' ');
            for (int j = 0; j < m; j++) {
                addChar(SYMBOLS.get(cells[i][j]), sb);
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getK() {
        return k;
    }
}
