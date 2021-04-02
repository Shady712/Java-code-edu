import java.io.*;
import java.util.*;

public class WordStatIndex {
	public static boolean check(char x) {
		return Character.isLetter(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'';
    }
	public static void update(String s, LinkedHashMap <String, IntList> m, int x) {
		if (m.get(s) == null) {
			m.put(s, new IntList());
		}
		m.get(s).add(x);
	}
	public static void main(String[] args) {
		LinkedHashMap <String, IntList> positions = new LinkedHashMap<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf-8"))) {
			int cur = reader.read();
			int position = 1;
			while (cur != -1) {
				StringBuilder buffer = new StringBuilder();
				while (cur != -1 && check((char) cur)) {
					buffer.append((char) cur);
					cur = reader.read();
				}
				if (buffer.length() > 0) {
					update(buffer.toString().toLowerCase(), positions, position++);
				}
				cur = reader.read();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try (Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8")) {
			for (String i : positions.keySet()) {
				writer.write(String.format(i + " " + positions.get(i).size() + " " + positions.get(i) + "\n"));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e ) {
			System.out.println(e.getMessage());
		}
	}
}