package system.exceptions;

/**
 * Exception Class for no king popular post.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class NoKingPopularPostException extends RuntimeException{

	/**
	 * If there are no posts, or no comments, on fakebook.
	 */
	private static final long serialVersionUID = 1L;
	public NoKingPopularPostException() {
		super();
	}
}
