package system.exceptions;

/**
 * Exception Class for user can not be the same.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class UserCanNotBeTheSameException extends RuntimeException {
	
	/**
	 * If the first and second users are the same.
	 */
	private static final long serialVersionUID = 137270238836618845L;

	public UserCanNotBeTheSameException() {
		super();
	}
}
