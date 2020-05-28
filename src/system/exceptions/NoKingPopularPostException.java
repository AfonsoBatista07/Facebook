package system.exceptions;

public class NoKingPopularPostException extends RuntimeException{

	/**
	 * If there are no posts, or no comments, on fakebook.
	 */
	private static final long serialVersionUID = 1L;
	public NoKingPopularPostException() {
		super();
	}
}
