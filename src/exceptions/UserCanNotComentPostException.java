package exceptions;

public class UserCanNotComentPostException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 112218804686830108L;
	private static final String ERROR = "%s cannot comment on this post!\n";
	public UserCanNotComentPostException() {
		super(ERROR);
	}
}
