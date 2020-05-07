package exceptions;

public class UserDoesNotExistExeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3285478462956516911L;
	
	private String userId;
	
	public UserDoesNotExistExeption(String userId) {
		super();
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
}
