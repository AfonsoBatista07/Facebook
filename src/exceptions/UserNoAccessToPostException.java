package exceptions;

public class UserNoAccessToPostException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525290982466754658L;
	
	private String idUserComment, idPost, idUserAuthor;
	
	public UserNoAccessToPostException(String idUserComment, String idPost, String idUserAuthor) {
		super();
		
		this.idUserComment = idUserComment;
		this.idPost = idPost;
		this.idUserAuthor = idUserAuthor;
	}
	
	public String getIdUserComment() {
		return idUserComment;
	}
	
	public String getIdPost() {
		return idPost;
	}
	
	public String getIdUserAuthor() {
		return idUserAuthor;
	}
	
}
