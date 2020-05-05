package exceptions;

public class UserHasNoPostsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2217283444498112887L;
	private static final String ERROR = "%s has no posts!\n";
	public UserHasNoPostsException() {
		super(ERROR);
	}
}
