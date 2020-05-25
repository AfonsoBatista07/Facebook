package system.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057201625646894706L;
	
	private String userId;
	
	public UserAlreadyExistsException(String userId) {
		super();
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
