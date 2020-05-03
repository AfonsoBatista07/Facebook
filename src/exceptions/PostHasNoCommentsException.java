package exceptions;

public class PostHasNoCommentsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2725549232612704858L;
	private static final String ERROR = "No comments!";
	public PostHasNoCommentsException() {
		super(ERROR);
	}
}
