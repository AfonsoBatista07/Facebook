package system.exceptions;

/**
 * Exception Class for unknown user kind.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class UnknownUserKindException extends RuntimeException{
	
	/**
	 * If the user kind is unknown.
	 */
	private static final long serialVersionUID = -177486162299409744L;
	
	public UnknownUserKindException() {
		super();
	}
}
