package system.exceptions;

public class NoPostsException extends RuntimeException{

	/**
	 * If the user exists, but has no posts.
	 */
	private static final long serialVersionUID = -7266553819067031902L;
	public NoPostsException() {
		super();
	}

}
