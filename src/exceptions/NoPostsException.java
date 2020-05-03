package exceptions;

public class NoPostsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7266553819067031902L;
	private static final String ERROR = "Social distancing has reached fakebook. Please post something.";
	public NoPostsException() {
		super(ERROR);
	}

}
