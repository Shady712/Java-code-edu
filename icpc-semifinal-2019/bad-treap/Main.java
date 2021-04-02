import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), t = -710 * 25000;
        for (int i = 0; i < n; i++) {
            System.out.println(t);
            t += 710;
        }
    }
}
