package user;

import java.util.Iterator;
import java.util.LinkedList;

public class FanaticClass extends UserClass{
	private LinkedList<String>loves, hates;
	private int numFanaticisms;
	
	public FanaticClass(String id, String type, int numFanaticisms, LinkedList<String> loves, LinkedList<String> hates) {
		super(id, type);
		this.numFanaticisms = numFanaticisms;
		this.loves = loves;
		this.hates = hates;
	}
	
	public int getNumFanaticisms() {
		return numFanaticisms;
	}
	
	public boolean loves(String love) {
		return find(love, loves);
	}
	
	public boolean hates(String hate) {
		return find(hate, hates);
	}
	
	private boolean find(String word, LinkedList<String> type) {
		Iterator<String> it = type.iterator();
		while(it.hasNext()) {
			String w = it.next();
			if(w.equalsIgnoreCase(word)) return true;
		}
		return false;
	}
}
