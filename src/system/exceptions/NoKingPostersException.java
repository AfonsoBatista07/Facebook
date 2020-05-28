package system.exceptions;

public class NoKingPostersException extends RuntimeException{
	/**
	 * If there are no posts on fakebook.
	 */
	private static final long serialVersionUID = 7559390067032292620L;
	public NoKingPostersException() {
		super();
	}
}
