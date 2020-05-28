package system.exceptions;

/**
 * Exception Class for invalid hashTags list.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class InvalidHashtagsListException extends RuntimeException {

	/**
	 * If the number of hashTags is not greater or equal to 0.
	 */
	private static final long serialVersionUID = 286286651199649578L;
	
	public InvalidHashtagsListException() {
		super();
	}
}
