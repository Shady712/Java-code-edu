import java.util.*;
import java.io.*;

public class MyScanner implements AutoCloseable {
	private BufferedReader reader;
	private boolean file;
	private int symbol;
	private boolean lines = true;
	
	public MyScanner() 
		throws IOException
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
		symbol = reader.read();
	}
	
	public MyScanner(String fileName, String coding) 
		throws FileNotFoundException, IOException
	{
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), coding));
		symbol = reader.read();
		file = true;
	}
	
	private boolean lineSeparator(char x) {
		return x == '\n' || x == '\r';
	}
	
	private boolean check(char x) {
		return Character.isLetterOrDigit(x) || Character.getType(x) == Character.DASH_PUNCTUATION || x == '\'';
	}
	
	private void nextLine() 
		throws IOException
	{
		symbol = reader.read();
		if (lineSeparator((char)symbol)) {
			symbol = reader.read();
		}
		if (symbol == -1) {
			lines = false;
		}
	}
	
	public boolean hasNextLine() {
		return lines;
	}
	
	private void skip() 
		throws IOException
	{
		while (symbol != -1 && !check((char)symbol)) {
			symbol = reader.read();
			if (lineSeparator((char)symbol)) {
				break;
			}
			if (symbol == -1) {
				lines = false;
			}
		}
	}
	
	public boolean hasNext() 
		throws IOException
	{
		if (symbol == -1) {
			return false;
		}
		if (lineSeparator((char)symbol)) {
			nextLine();
			return false;
		}
		skip();
		if (lineSeparator((char)symbol)) {
			nextLine();
			return false;
		}
		return check((char)symbol);
	}
	
	public String next() 
		throws IOException
	{
		skip();
		StringBuilder builder = new StringBuilder();
		while (symbol != -1 && check((char)symbol)) {
			builder.append((char)symbol);
			symbol = reader.read();
			if (symbol == -1) {
				lines = false;
			}
		}
		return builder.toString();
	}
	
	public int nextInt() 
		throws IOException
	{
		return Integer.parseInt(next());
	}
	
	public void close() 
		throws IOException
	{
		if (file) {
			reader.close();
		}
	}
}