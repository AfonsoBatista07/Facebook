package exceptions;

public class UsersAlreadyFriendsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5565779026114868383L;
	
	private String firstUserId, secondUserId;
	
	public UsersAlreadyFriendsException(String firstUserId, String secondUserId) {
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
