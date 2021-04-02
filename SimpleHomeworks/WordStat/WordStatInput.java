import java.io.*;
import java.util.*;

public class WordStatInput {
    public static void main(String[] args) {
	try {
    	    Reader reader = new InputStreamReader(new FileInputStream(args[0]), "utf-8");
	    try {
		StringBuilder builder = new StringBuilder();
		BufferedReader buffer = new BufferedReader(reader);
		while (true) {
		    String tmp = buffer.readLine();
		    if (tmp == null) {
			break;
		    }
		    builder.append(tmp);
		    builder.append("\n");
		}
		String input = new String(builder.toString().toLowerCase());
		Map <String, Integer> numbers = new LinkedHashMap<>();
		for (int i = 0; i < input.length(); i++) {
		    if (Character.isLetter(input.charAt(i)) || Character.getType(input.charAt(i)) == Character.DASH_PUNCTUATION || input.charAt(i) == '\'') {
			int l = i;
			int r = i;
			while (r < input.length() && (Character.isLetter(input.charAt(r)) || Character.getType(input.charAt(r)) == Character.DASH_PUNCTUATION || input.charAt(r) == '\'')) {
			    r++;
			}
			String tmp = input.substring(l, r);
			Integer number = numbers.get(tmp);
			if (number != null) {
			    numbers.put(tmp, number + 1);
			} else {
			    numbers.put(tmp, 1);
			}
			i = r - 1;
		    }
		}
		try {
		    Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8");
		    try {
			Set<String> words = numbers.keySet();
		    	for (String tmp : words) {
			    writer.write(tmp + " " + numbers.get(tmp) + "\n");
 		    	}
		    } finally {
			writer.close();
		    }
		} catch (UnsupportedEncodingException e) {
		    System.out.println(e.getMessage());
		}
	    } catch (OutOfMemoryError e) {
		System.out.println(e.getMessage());
	    } finally {
		reader.close();
	    }
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	} catch (UnsupportedEncodingException e) {
	    System.out.println(e.getMessage());
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }
}