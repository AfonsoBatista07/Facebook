package exceptions;

public class UserDoesNotExistException extends RuntimeException{
	private static final long serialVersionUID = 2398705410217513356L;
	/**
	 * 
	 */
	
	private String firstUserId, secondUserId;
	
	public UserDoesNotExistException(String firstUserId, String secondUserId) {
		super();
		this.firstUserId = firstUserId;
		this.secondUserId = secondUserId;
	}
	
	public String getFirsUserId() {
		return firstUserId;
	}
	
	public String getSecondUserId() {
		return secondUserId;
	}
}

