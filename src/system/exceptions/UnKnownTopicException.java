package system.exceptions;

/**
 * Exception Class for unknown topic.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class UnKnownTopicException extends RuntimeException {
	
	/**
	 * If the topic id a is unknown.
	 */
	private static final long serialVersionUID = -4242216562100691665L;
	public UnKnownTopicException() {
		super();
	}
}
