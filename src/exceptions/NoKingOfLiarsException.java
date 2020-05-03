package exceptions;

public class NoKingOfLiarsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3849345493900718301L;
	private static final String ERROR = "Social distancing has reached fakebook. Post a lie and become the king of liars.";
	public NoKingOfLiarsException() {
		super(ERROR);
	}
}
