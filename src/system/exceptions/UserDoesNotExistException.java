package system.exceptions;

/**
 * Exception Class for user does not exists.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class UserDoesNotExistException extends RuntimeException {

	/**
	 * If the user id of either the author of the comment, or the author of the post is unknown.
	 */
	private static final long serialVersionUID = -3285478462956516911L;
	
	private String userId;
	
	/**
	 * Exception constructor.
	 * @param userId - User Id that does not exist.
	 */
	public UserDoesNotExistException(String userId) {
		super();
		this.userId = userId;
	}
	
	/**
	 * @return the user Id that does not exist.
	 */
	public String getUserId() {
		return userId;
	}
}
