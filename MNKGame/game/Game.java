package game;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        if (player1 == null || player2 == null) {
            throw new NullPointerException("Invalid game without a player");
        }
    }

    public Result play(Board board) {
        while (true) {
            final Result result1 = move(board, player1, 1);
            if (result1 != Result.UNKNOWN && result1 != Result.EXTRA_MOVE) {
                return result1;
            }
            if (result1 != Result.EXTRA_MOVE) {
                Result result2 = Result.EXTRA_MOVE;
                while (result2 == Result.EXTRA_MOVE) {
                    result2 = move(board, player2, 2);
                    if (result2 != Result.UNKNOWN && result2 != Result.EXTRA_MOVE) {
                        return result2;
                    }
                }
            }
        }
    }

    private Result move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
        } else if (result == Result.DRAW) {
            log("Draw");
        }
        return result;
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
