import java.util.*;
import java.io.*;

public class ReverseHexAbc {
	
	public static char convert(char x) {
		return (char) (x - ('a' - '0'));
	}
	
	public static String convert(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '+' || s.charAt(i) == '-') {
				builder.append(s.charAt(i));
			} else {
				builder.append(convert(s.charAt(i)));
			}
		}
		return builder.toString();
	}
	
	public static int toInt(String s) {
		if (s.length() < 2) {
			return Integer.parseInt(convert(s));
		}
		if (s.charAt(0) == '0' && s.charAt(1) == 'x') {
			return Integer.parseUnsignedInt(s.substring(2), 16);
		} else {
			return Integer.parseInt(convert(s));
		}
	}
	
	public static void main(String args[]) throws IOException {
		MyScanner input = new MyScanner();
		List<int[]> array = new ArrayList<>();
		while (input.hasNextLine()) {
			int k = 0;
			int[] cur = new int[1];
			while (input.hasNext()) {
				if (k == cur.length) {
					cur = Arrays.copyOf(cur, cur.length * 2);
				}
				cur[k++] = toInt(input.next().toLowerCase());
			}
			cur = Arrays.copyOf(cur, k);
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