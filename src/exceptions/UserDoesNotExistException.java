package exceptions;

import user.User;

public class UserDoesNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285478462956516911L;
	
	private User user;
	
	public UserDoesNotExistException(User user) {
		super();
		this.user = user;
	}
	
	public String getUserId() {
		return user.getId();
	}
}
