package system.exceptions;

public class UserCanNotBeTheSameException extends RuntimeException{
	
	/**
	 * If the first and second users are the same.
	 */
	private static final long serialVersionUID = 137270238836618845L;

	public UserCanNotBeTheSameException() {
		super();
	}
}
