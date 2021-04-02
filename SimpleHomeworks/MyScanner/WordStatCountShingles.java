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
		LinkedHashMap<String, Integer> numbers = new LinkedHashMap<>();
		try (MyScanner reader = new MyScanner(args[0], "utf-8")) {
			while (reader.hasNextLine()) {
				while (reader.hasNext()) {
					String word = reader.next().toLowerCase();
					StringBuilder substr = new StringBuilder();
					for (int i = 0; i < word.length(); i++) {
						if (check(word.charAt(i))) {
							substr.append(word.charAt(i));
						} else {
							substr = new StringBuilder();
						}
						if (substr.length() == 3) {
							update(substr.toString(), numbers);
							substr.deleteCharAt(0);
						}
					}
				}
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
				writer.write(String.format("%s %d\n", i, numbers.get(i)));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}