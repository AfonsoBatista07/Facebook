package exceptions;

public class InvalidFanaticismListException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4730950725835496032L;
	private static final String ERROR = "Invalid fanaticism list!";
	public InvalidFanaticismListException() {
		super(ERROR);
	}
}
