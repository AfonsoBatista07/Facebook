package exceptions;

public class UserNoAccessToPostException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525290982466754658L;
	private static final String ERROR = "%s has no access to post %s by %s!!";
	public UserNoAccessToPostException() {
		super(ERROR);
	}
}
