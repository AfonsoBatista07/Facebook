package exceptions;

public class UserHasNoPostsException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2217283444498112887L;
	
	private String idUserAuthor, idPost;
	
	public UserHasNoPostsException(String idUserAuthor, String idPost) {
		super();
		
		this.idUserAuthor = idUserAuthor;
		this.idPost = idPost;
	}
	
	public String getIdUserAuthor() {
		return idUserAuthor;
	}
	
	public String getIdPost() {
		return idPost;
	}
}
