package system.exceptions;

public class InvalidHashtagsListException extends RuntimeException {

	/**
	 * If the number of hashTags is not greater or equal to 0.
	 */
	private static final long serialVersionUID = 286286651199649578L;
	
	public InvalidHashtagsListException() {
		super();
	}
}
