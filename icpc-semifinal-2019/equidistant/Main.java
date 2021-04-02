import java.io.*;
import java.util.*;

public class Main {
    static int[] d, p;
    static int n, m;
    static List<Integer>[] ways;
    public static void dfs(int apex) {
        for (int x : ways[apex]) {
            if (d[x] == -1) {
                d[x] = d[apex] + 1;
                p[x] = apex;
                dfs(x);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        ways = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            ways[i] = new LinkedList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int x, y;
            x = nextInt() - 1;
            y = nextInt() - 1;
            ways[x].add(y);
            ways[y].add(x);
        }
        int[] cities = new int[m];
        for (int i = 0; i < m; i++) {
            cities[i] = nextInt() - 1;
        }
        d = new int[n];
        p = new int[n];
        Arrays.fill(d, -1);
        d[cities[0]] = 0;
        dfs(cities[0]);
        int dist = -1, apex = 0;
        for (int i = 0; i < m; i++) {
            if (d[cities[i]] > dist) {
                dist = d[cities[i]];
                apex = cities[i];
            }
        }
        if (dist % 2 > 0) {
            System.out.println("NO");
            return;
        }
        for (int i = 0; i < dist / 2; i++) {
            apex = p[apex];
        }
        Arrays.fill(d, -1);
        d[apex] = 0;
        dfs(apex);
        for (int i = 1; i < m; i++) {
            if (d[cities[i]] != d[cities[0]]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.println(apex + 1);
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
