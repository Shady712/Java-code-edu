import java.util.*;
import java.io.*;

public class Reverse {
    public static void main(String[] args)
	throws IOException
    {
	Scanner input = new Scanner(System.in);
	List<int[]> array = new ArrayList<>();
	int maxLen = 0;
	while (input.hasNextLine()) {
	    Scanner tmp = new Scanner(input.nextLine());
	    int k = 0;
	    int[] cur = new int[1];
	    while (tmp.hasNextInt()) {
	        if (k == cur.length) {
		    cur = Arrays.copyOf(cur, cur.length * 2);
		}
		cur[k++] = tmp.nextInt();
	    }
	    cur = Arrays.copyOf(cur, k);
	    maxLen = Math.max(maxLen, k);
	    array.add(cur);
	}
	for (int i = array.size() - 1; i >= 0; i--) {
	    for (int j = array.get(i).length - 1; j >= 0; j--) {
		System.out.print(array.get(i)[j] + " ");
	    }
	    System.out.println();
	}
    }
}