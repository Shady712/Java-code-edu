import java.util.*;

public class LineCounter {
	private int count;
	private IntList list;
	public int lastEntry;
	
	public LineCounter() {
		lastEntry = 0;
		count = 0;
		list = new IntList();
	}
	
	public void add() {
		count++;
	}
	
	public void addList(int x) {
		list.add(x);
	}
	
	public int getCount() {
		return count;
	}
	
	public IntList getList() {
		return list;
	}
}