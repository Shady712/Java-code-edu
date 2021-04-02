import java.io.*;
import java.util.*;

public class WordStatCountFirstIndex {
	public static boolean check(char x) {
		return Character.isLetter(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'';
	}
	public static boolean checkWord(String word, int position, Map<String, LineCounter> m, int line) {
		if (word.length() == 0) {
			return false;
		}
		LineCounter cur = m.getOrDefault(word, new LineCounter());
		cur.add();
		if (cur.lastEntry != line) {
			cur.addList(position);
			cur.lastEntry = line;
		}
		m.put(word, cur);
		return true;
	}

	public static void main(String[] args) {
		Map<String, LineCounter> positions = new LinkedHashMap<>();
		int line = 0;
		try (MyScanner reader = new MyScanner(args[0], "utf-8")) {
			while (reader.hasNextLine()) {
				line++;
				int position = 1;
				while (reader.hasNext()) {
					String string = reader.next().toLowerCase();
					StringBuilder word = new StringBuilder();
					for (int i = 0; i < string.length(); i++) {
						if (check(string.charAt(i))) {
							word.append(string.charAt(i));
						} else {
							if (checkWord(word.toString(), position, positions, line)) {
								position++;
							}
							word.setLength(0);
						}
					}
					if (checkWord(word.toString(), position, positions, line)) {
						position++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Input reading error: " + e.getMessage());
		}
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8")) {
			List<String> words = new LinkedList<>();
			for (String tmp : positions.keySet()) {
				words.add(tmp);
			}
			Comparator<String> getOrder = (o1, o2) -> Integer.compare(positions.get(o1).getCount(), positions.get(o2).getCount());
			Collections.sort(words, getOrder);
			for (String i : words) {
				writer.write(String.format("%s %d %s\n", i, positions.get(i).getCount(), positions.get(i).getList()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Output file not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Output writing error: " + e.getMessage());
		}
	}
}