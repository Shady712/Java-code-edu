import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] a = new int[n];
        a[0] = nextInt();
        int max = a[0];
        for (int i = 1; i < n; i++) {
            a[i] = nextInt();
            max = Math.max(max, a[i]);
            a[i] += a[i - 1];
        }
        int[] pr = new int[(int)1e6 + 5];
        int q = nextInt();
        for (int i = 0; i < q; i++) {
            int t = nextInt();
            if (t < max) {
                System.out.println("Impossible");
                continue;
            }
            if (pr[t] != 0) {
                System.out.println(pr[t]);
                continue;
            }
            int val = 0, p = 0, ans = 0;
            while (p < n) {
                int l = p, r = n - 1, id = p;
                while (l <= r) {
                    int c = (l + r) / 2;
                    if (a[c] > t + val) {
                        r = c - 1;
                    } else {
                        id = c;
                        l = c + 1;
                    }
                }
                p = id + 1;
                val = a[id];
                ans++;
            }
            pr[t] = ans;
            System.out.println(ans);
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
