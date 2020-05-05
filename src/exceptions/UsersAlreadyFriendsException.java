package exceptions;

public class UsersAlreadyFriendsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565779026114868383L;
	private static final String ERROR = "%s must really admire %s!\n";
	public UsersAlreadyFriendsException() {
		super(ERROR);
	}
}
