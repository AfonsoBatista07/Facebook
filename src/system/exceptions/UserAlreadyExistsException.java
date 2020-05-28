package system.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
	
	/**
	 * If there is already a user with the same id.
	 */
	private static final long serialVersionUID = -3057201625646894706L;
	
	private String userId;
	
	/**
	 * Exception constructor.
	 * @param userId - User Id that already exist.
	 */
	public UserAlreadyExistsException(String userId) {
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
