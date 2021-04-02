import java.util.*;
import java.io.*;

public class ReverseAvg {
    public static void main(String[] args)
		throws IOException
    {
	MyScanner input = new MyScanner();
	List<int[]> array = new ArrayList<>();
	int maxLen = 0;
	while (input.hasNextLine()) {
	    MyScanner tmp = new MyScanner(input.nextLine());
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

	long[] lines = new long[array.size()];
	long[] columns = new long[maxLen];
	int[] number = new int[maxLen];
	for (int i = 0; i < array.size(); i++) {
	    for (int j = 0; j < array.get(i).length; j++) {
		lines[i] += array.get(i)[j];
	    	number[j]++;
		columns[j] += array.get(i)[j];
            }
	}
	
	for (int i = 0; i < array.size(); i++) {
	    for (int j = 0; j < array.get(i).length; j++) {
	        System.out.print((lines[i] + columns[j] - array.get(i)[j]) / (array.get(i).length + number[j] - 1) + " ");
	    }
	    System.out.println();
	}
    }
}