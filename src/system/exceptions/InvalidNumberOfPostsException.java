package system.exceptions;

/**
 * Exception Class for invalid number of posts.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class InvalidNumberOfPostsException extends RuntimeException {
	
	/**
	 * If the maximum number of posts to list is not greater or equal to 1.
	 */
	private static final long serialVersionUID = 970572607340229809L;

	public InvalidNumberOfPostsException() {
		super();
	}
}
