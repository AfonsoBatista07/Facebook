package system.exceptions;

/**
 * Exception Class for invalid fanaticism list.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class InvalidFanaticismListException  extends RuntimeException{
	
	/**
	 * If the user is a fanatic but there are repeated fanaticisms in the users list.
	 */
	private static final long serialVersionUID = 4730950725835496032L;
	public InvalidFanaticismListException() {
		super();
	}
}
