package system.exceptions;

/**
 * Exception Class for no posts.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class NoPostsException extends RuntimeException{

	/**
	 * If the user exists, but has no posts.
	 */
	private static final long serialVersionUID = -7266553819067031902L;
	public NoPostsException() {
		super();
	}

}
