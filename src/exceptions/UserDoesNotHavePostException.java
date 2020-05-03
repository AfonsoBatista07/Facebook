package exceptions;

public class UserDoesNotHavePostException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1369001452720033765L;
	private static final String ERROR = "%s is an invalid user kind!";
	public UserDoesNotHavePostException() {
		super(ERROR);
	}
}
