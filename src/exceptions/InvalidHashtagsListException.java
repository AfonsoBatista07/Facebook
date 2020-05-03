package exceptions;

public class InvalidHashtagsListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 286286651199649578L;
	
	private static final String ERROR = "Invalid hashtags list!";
	public InvalidHashtagsListException() {
		super(ERROR);
	}
}
