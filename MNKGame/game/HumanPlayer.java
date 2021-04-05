package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    private Move readMove(Cell cell) {
        if (!in.hasNextLine()) {
            throw new RuntimeException("Input is ended, but the game is not");
        }
        final Scanner sc = new Scanner(in.nextLine());
        final int r, c;
        if (sc.hasNextInt()) {
            r = sc.nextInt();
        } else {
            r = -1;
        }
        if (sc.hasNextInt()) {
            c = sc.nextInt();
        } else {
            c = -1;
        }
        if (sc.hasNext()) {
            return new Move(-1, -1, cell);
        }
        return new Move(r, c, cell);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        int countInvalid = 0;
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            final Move move = readMove(cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
            countInvalid++;
            if (countInvalid >= 3) {
                return move;
            }
        }
    }
}
