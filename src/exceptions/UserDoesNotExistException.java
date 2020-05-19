package exceptions;

import user.User;

public class UserDoesNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285478462956516911L;
	
	private String userId;
	
	public UserDoesNotExistException(String userId) {
		super();
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
