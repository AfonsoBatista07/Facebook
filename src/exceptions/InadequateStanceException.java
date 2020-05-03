package exceptions;

public class InadequateStanceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6833999717529530423L;
	private static final String ERROR = "%s is an invalid user kind!";
	public InadequateStanceException() {
		super(ERROR);
	}
}
