package exceptions;

public class InvalidCommentStanceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -683399971752953043L;
	private static final String ERROR = "Invalid comment stance!";
	public InvalidCommentStanceException() {
		super(ERROR);
	}
}
