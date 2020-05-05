package exceptions;

public class UserDoesNotExistExeption extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285478462956516911L;
	private static final String ERROR = "%s does not exist!";
	
	public UserDoesNotExistExeption() {
		super(ERROR);
	}
}
