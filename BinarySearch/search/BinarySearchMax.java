package search;

public class BinarySearchMax {

    // Let's define immutable : forall i = 1..n array[i] = array'[i] && exists i: array(0, .. i) is increasing, array(i + 1, length) is decreasing
    /*
        Pred: array.length >= 1 && left <= right && left >= 0
        Post: R == id : array[id] = max(array[0], ... array[array.length - 1])
     */
    private static int recursiveSearch(int[] array, int left, int right) {
        // Invariant: answer is between left and right && immutable
        if (left == right) {
            // answer is between left and right -> returning the answer
            return left;
        }
        int middle = (left + right) / 2;
        /*
            Pred: left <= middle < middle + 1 <= right
            Post: if (array[middle] < array[middle + 1]) R == left == middle + 1    else R == right == middle
         */
        if (array[middle] < array[middle + 1]) {
            // middle and middle + 1 are parts of increasing array -> forall: i < middle: array[i] < array[middle] -> answer is between middle + 1 and right
            return recursiveSearch(array, middle + 1, right);
            // answer is between left and right -> +inv
        } else {
            // middle and middle + 1 are parts of decreasing array -> forall: i > middle + 1 array[i] < array[middle + 1] -> answer is between left and middle
            return recursiveSearch(array, left, middle);
            // answer is between left and right -> +inv
        }
    }

    /*
        Pred: forall i: array[i] is integer && exists i: array(0, .. i) is increasing, array(i + 1, length) is decreasing
        Post: Post: R == id : array[id] = max(array[0], ... array[array.length - 1])
     */
    private static int recursiveSearch(int[] array) {
        return recursiveSearch(array, 0, array.length - 1);
    }

    /*
        Pred: array.length >= 1 && forall i: array[i] is integer and exists i: array(0, .. i) is increasing, array(i + 1, length) is decreasing
        Post: R == left : array[left] = max(array[0], ..., array[length - 1])
     */
    private static int iterativeSearch(int[] array) {
        // right >= left >= 0
        int left = 0, right = array.length - 1;
        // Invariant: answer is between left and right (left == right -> left is the answer) && immutable
        while (left < right) {
            int middle = (left + right) / 2;
            /*
                Pred: left <= middle < middle + 1 <= right
                Post: if (array[middle] < array[middle + 1]) R == left == middle + 1    else R == right == middle
             */
            if (array[middle] < array[middle + 1]) {
                // middle and middle + 1 are parts of increasing array -> forall: i < middle: array[i] < array[middle] -> answer is between middle + 1 and right
                left = middle + 1;
                // answer is between left and right -> +inv
            } else {
                // middle and middle + 1 are parts of decreasing array -> forall: i > middle + 1 array[i] < array[middle + 1] -> answer is between left and middle
                right = middle;
                // answer is between left and right -> +inv
            }
        }
        // left == right and answer is between left and right -> left is the answer
        return left;
    }

    /*
        Pred: args != null && args.length > 0 && forall i: 0 <= i < args.length : args[i] is integer (not null)
        Post: R == max(args[0], ... , args[args.length - 1])
     */
    public static void main(String[] args) {
        int[] array = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            array[i] = Integer.parseInt(args[i]);
        }
        int itAns = iterativeSearch(array);
        int recAns = recursiveSearch(array);
        if (recAns != itAns) {
            System.out.println("Something went wrong...");
        }
        System.out.println(array[recAns]);
    }
}
