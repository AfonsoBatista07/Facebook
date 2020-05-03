package exceptions;

public class UserAlreadyResgiteredException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057201625646894706L;
	private static final String ERROR = "%s already exists!";
	public UserAlreadyResgiteredException() {
		super(ERROR);
	}
}
