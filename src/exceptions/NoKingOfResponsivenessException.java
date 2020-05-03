package exceptions;

public class NoKingOfResponsivenessException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3957434001609057602L;
	private static final String ERROR = "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";
	public NoKingOfResponsivenessException() {
		super(ERROR);
	}
}
