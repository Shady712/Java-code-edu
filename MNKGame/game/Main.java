package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter game type: Romb or mnk");
        String type = in.next();
        if (!type.equals("Romb") && !type.equals("mnk")) {
            throw new AssertionError("Unknown game type");
        }
        System.out.println("Enter n, k numbers");
        final int extra = 4, n = in.nextInt(), k = in.nextInt();
        if (n <= 0 || k <= 0) {
            throw new AssertionError("Invalid n, k numbers");
        }
        final Game game = new Game(true, new HumanPlayer(), new SequentialPlayer());
        if (type.equals("Romb")) {
            game.play(new RhombusBoard(n, k, extra));
        } else {
            System.out.println("Enter m number");
            final int m = in.nextInt();
            if (m <= 0) {
                throw new AssertionError("Invalid m number");
            }
            game.play(new MNKBoard(m, n, k, extra));
        }
    }
}
