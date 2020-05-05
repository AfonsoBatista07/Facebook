package exceptions;

public class UnknownUserKindException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -177486162299409744L;
	private static final String ERROR = "%s is an invalid user kind!";
	
	public UnknownUserKindException() {
		super(ERROR);
	}
}
