package search;

public class BinarySearch {

    // Pred: array.length >= 1
    // Post: array[R] <= args[0] && R is minimal
    private static int recursiveSearch(int x, int[] array, int left, int right) {
        // Invariant: answer is between left and right or equals to right + 1 if left > right && array is immutable
        if (left > right) {
            // if invariant is right then right + 1 is the answer (right + 1 is the upper bound if for all j array[j] > x)
            return right + 1;
        }
        int middle = (left + right) / 2;
        if (array[middle] <= x) {
            // middle is a possible answer
            return recursiveSearch(x, array, left, middle - 1);
            // answer is between left and right == middle - 1 or equals to right + 1 == middle if for all j: left <= j <= right: array[j] > x
        } else {
            // for all j <= middle array[j] > x --> answer is somewhere in the right
            return recursiveSearch(x, array, middle + 1, right);
        }
        // answer is between left and right or equals to right + 1 if left > right (for all j: left <= j <= right: array[j] > x) --> +inv
    }

    // Pred: array.length >= 1
    // Post: array[R] <= args[0] && R is minimal
    private static int recursiveSearch(int x, int[] array) {
        // Array is sorted in non-increasing order
        return recursiveSearch(x, array, 0, array.length - 1);
    }

    // Pred: array.length >= 1
    // Post: array[R] <= args[0] && R is minimal
    private static int iterativeSearch(int x, int[] array) {
        // Array is sorted in non-increasing order
        int left = 0, right = array.length - 1, ans = right + 1;
        // Invariant: answer is between left and right or found when left > right && array is immutable
        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] <= x) {
                // middle is a possible answer
                ans = middle;
                // for all j >= middle array[j] <= x --> the lesser answer can only be in the left
                right = middle - 1;
                // answer is between left and right or found if left > right --> +inv
            } else {
                // for all j <= middle array[j] > x --> answer is somewhere in the right
                left = middle + 1;
                // answer is between left and right or found if left > right --> +inv
            }
        }
        // ans is the least index such that array[ans] <= x (or the upper bound if no such element exists)
        return ans;
    }

    // Pred: args.length >= 2 && forall i = 0..args.length - 1 : args[i] is integer (not null)
    // Post: array[R] <= args[0] && R is minimal
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] array = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
        int itAns = iterativeSearch(x, array);
        int recAns = recursiveSearch(x, array);
        if (itAns != recAns) {
            System.out.println("Something went wrong...");
        } else {
            System.out.println(itAns);
        }
    }
}
