package user;

import java.util.Iterator;

/**
 * Interface of the FanaticClass.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface Fanatic extends User{
	
	/**
	 * @return Iterator of the fanaticisms.
	 */
	public Iterator<String> getFanaticisms();
}
