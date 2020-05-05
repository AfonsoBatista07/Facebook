package exceptions;

public class InadequateStanceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6833999717529530423L;
	private static final String ERROR = "Invalid comment stance!";
	public InadequateStanceException() {
		super(ERROR);
	}
}
