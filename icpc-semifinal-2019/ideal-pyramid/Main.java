import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int symbol = 1;
    static boolean lines = true;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int xLeft = (int)1e9, xRight = (int)-1e9;
        int yLeft = (int)1e9, yRight = (int)-1e9;
        for (int i = 0 ; i < n; i++) {
            int x = nextInt(), y = nextInt(), h = nextInt();
            xLeft = Math.min(xLeft, x - h);
            xRight = Math.max(xRight, x + h);
            yLeft = Math.min(yLeft, y - h);
            yRight = Math.max(yRight, y + h);
        }
        System.out.println((xLeft + xRight) / 2 + " " + (yLeft + yRight) / 2 + " " + Math.max(xRight - xLeft + 1, yRight - yLeft + 1) / 2);
    }

    // Fast input reading
    private static boolean lineSeparator(char x) {
        return x == '\n' || x == '\r';
    }

    private static void nextLine() throws IOException {
        symbol = reader.read();
        while (symbol != -1 && lineSeparator((char)symbol)) {
            symbol = reader.read();
        }
        if (symbol == -1) {
            lines = false;
        }
    }

    public static boolean hasNextLine() {
        return lines;
    }

    private static boolean check(char x) {
        return Character.isLetterOrDigit(x) || Character.getType(x) == Character.DASH_PUNCTUATION;
    }

    private static void skip() throws IOException {
        while (symbol != -1 && !check((char)symbol)) {
            symbol = reader.read();
            if (symbol == -1) {
                lines = false;
            }
        }
    }

    public static boolean hasNext() throws IOException {
        if (symbol == -1) {
            return false;
        }
        if (lineSeparator((char)symbol)) {
            nextLine();
            return false;
        }
        skip();
        if (lineSeparator((char)symbol)) {
            nextLine();
            return false;
        }
        return check((char)symbol);
    }

    public static String next() throws IOException {
        skip();
        StringBuilder builder = new StringBuilder();
        while (symbol != -1 && check((char)symbol)) {
            builder.append((char)symbol);
            symbol = reader.read();
            if (symbol == -1) {
                lines = false;
            }
        }
        return builder.toString();
    }

    public static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
