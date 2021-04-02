import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String tmp = next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tmp.charAt(j) - '0';
            }
        }
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] > 0) {
                    graph[i][j] = 1;
                    for (int go = j + 1; go < n; go++) {
                        matrix[i][go] = (matrix[i][go] + 10 - matrix[j][go]) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
    }

    // Fast input reading
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int symbol = 1;
    static boolean lines = true;


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
