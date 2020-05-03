package exceptions;

public class UserNotRegisteredException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 13143769754901513L;
	private static final String ERROR = "%s does not exist!";
	public UserNotRegisteredException() {
		super(ERROR);
	}
}
