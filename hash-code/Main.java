import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            solve(i);
        }
    }

    private static void solve(int x) {
        StringBuilder ans = new StringBuilder();
        List<Integer> chars = getList(x);
        for (int i : chars) {
            if (i == 0) {
                ans.append("by");
            } else {
                ans.append("cZ");
            }
        }
        System.out.println(ans.toString());
    }

    private static List<Integer> getList(int x) {
        List<Integer> ans = new ArrayList<>();
        while (x > 0) {
            ans.add(x % 2);
            x /= 2;
        }
        while (ans.size() < 12) {
            ans.add(0);
        }
        return ans;
    }
}
