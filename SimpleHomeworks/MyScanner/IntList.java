import java.util.*;

public class IntList {
	int[] array;
	private int k;
	
	void add(int x) {
		array[k++] = x;
		if (k == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}
	}
	public String toString() {
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < k - 1; i++) {
			ans.append(String.format(array[i] + " "));
		}
		ans.append(array[k - 1]);
		return ans.toString();
	}
	public int size() {
		return k;
	}
	public IntList() {
		k = 0;
		array = new int[1];
	}
}