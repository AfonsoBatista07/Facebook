package system.exceptions;

/**
 * Exception Class for no king posters.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class NoKingPostersException extends RuntimeException{
	/**
	 * If there are no posts on fakebook.
	 */
	private static final long serialVersionUID = 7559390067032292620L;
	public NoKingPostersException() {
		super();
	}
}
