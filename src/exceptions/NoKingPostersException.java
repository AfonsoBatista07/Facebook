package exceptions;

public class NoKingPostersException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7559390067032292620L;
	private static final String ERROR = "Social distancing has reached fakebook. Post something the king of posters.";
	public NoKingPostersException() {
		super(ERROR);
	}
}
