package system.exceptions;

public class UserNoAccessToPostException extends RuntimeException{
	
	/**
	 * If the user cannot comment that post, because he has not received it from the author.
	 */
	private static final long serialVersionUID = 525290982466754658L;
	
	public UserNoAccessToPostException() {
		super();
	}
	
}
