package exceptions;

public class UnKnownTopicException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4242216562100691665L;
	private static final String ERROR = "Oh please, whp would write about %s?";
	public UnKnownTopicException() {
		super(ERROR);
	}
}
