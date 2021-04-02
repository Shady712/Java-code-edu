import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 998244353;
    static long n, k;

    public static LinkedList<Long> decompose(long x) {
        LinkedList<Long> ans = new LinkedList<>();
        ans.add((long) 1);
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                ans.add(i);
                if (x / i != i) {
                    ans.add(x / i);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        k = nextInt();
        long[] powers = new long[(int)n / 2 + 5];
        powers[0] = 1;
        for (int i = 1; i < n / 2 + 5; i++) {
            powers[i] = (powers[i - 1] * k) % MOD;
        }
        long[] R = new long[(int)n + 5];
        for (int i = 0; i <= n; i++) {
            long tmp;
            if (i % 2 > 0) {
                tmp = i * powers[i / 2 + 1];
            } else {
                tmp = (i / 2) * (powers[i / 2] + powers[i / 2 + 1]);
            }
            R[i] = tmp % MOD;
        }
        long[] D = new long[(int)n + 5];
        D[0] = 0;
        D[1] = k;
        for (int i = 2; i <= n; i++) {
            List<Long> div = decompose(i);
            for (long l : div) {
                D[i] = (D[i] + D[(int)l] * i / l) % MOD;
            }
            D[i] = (R[i] + MOD - D[i]) % MOD;
        }
        long ans = k;
        for (int i = 2; i <= n; i++) {
            ans = (ans + D[i]) % MOD;
            List<Long> div = decompose(i);
            for (long l : div) {
                ans = (ans + D[(int)l]) % MOD;
            }
        }
        System.out.println(ans);
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
