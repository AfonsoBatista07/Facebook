package exceptions;

public class UserAlreadyExistsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057201625646894706L;
	private static final String ERROR = "%s already exists!";
	
	public UserAlreadyExistsException() {
		super(ERROR);
	}
}
