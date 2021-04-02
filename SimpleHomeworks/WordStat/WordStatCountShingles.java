import java.io.*;
import java.util.*;

public class WordStatCountShingles {
    public static boolean check(char x) {
		return Character.isLetter(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'';
    }
    public static void update(String s, LinkedHashMap<String, Integer> m) {
		m.put(s, m.getOrDefault(s, 0) + 1);
    }
    public static void main(String[] args) {
		LinkedHashMap <String, Integer> numbers = new LinkedHashMap<>();
		try (BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(args[0]), "utf-8"))) {
			int cur = reader.read();
			while (cur != -1) {
				StringBuilder buffer = new StringBuilder();
				while (cur != -1 && check((char) cur)) {
					buffer.append((char)cur);
					if (buffer.length() == 3) {
						update(buffer.toString().toLowerCase(), numbers);
						buffer.deleteCharAt(0);
					}
					cur = reader.read();
				}
				cur = reader.read();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String[] words = new String[numbers.size()];
		int k = 0;
		for (String tmp : numbers.keySet()) {
			words[k++] = tmp;
		}
		Comparator<String> getOrder = (o1, o2) -> Integer.compare(numbers.get(o1), numbers.get(o2));
		Arrays.sort(words, getOrder);
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8")) {
			for (String i : words) {
				writer.write(String.format(i + " " + numbers.get(i) + "\n"));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}