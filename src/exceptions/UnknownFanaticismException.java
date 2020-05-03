package exceptions;

public class UnknownFanaticismException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -774818344846294320L;
	private static final String ERROR = "Oh please, who would be a fanatic of %s?";
	public UnknownFanaticismException() {
		super(ERROR);
	}
}
