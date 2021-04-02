import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String inputArray;
    static int inputIndex;
    static int symbol;
    static boolean lines = true;


    public static void solve() throws IOException {
        int n = nextInt();
        nextLine();
        int[] array = new int[n];
        for (int i = 0; i < n && hasNext(); i++) {
            array[i] = nextInt();
        }
        nextLine();
        int ans = 0;
        Map<Integer, Integer> kValues = new HashMap<>();
        for (int j = n - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                int key = 2 * array[j] - array[i];
                if (kValues.containsKey(key)) {
                    ans += kValues.get(key);
                }
            }
            kValues.put(array[j], kValues.getOrDefault(array[j], 0) + 1);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        int quests = nextInt();
        nextLine();
        for (int i = 0; i < quests; i++) {
            solve();
        }
    }


    // Fast input reading
    private static boolean lineSeparator(char x) {
        return x == '\n' || x == '\r';
    }

    private static void nextLine() throws IOException {
        symbol = reader.read();
        if (lineSeparator((char)symbol)) {
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
        return Character.isLetterOrDigit(x);
    }

    private static void skip() throws IOException {
        while (symbol != -1 && !check((char)symbol)) {
            symbol = reader.read();
            if (lineSeparator((char)symbol)) {
                break;
            }
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
