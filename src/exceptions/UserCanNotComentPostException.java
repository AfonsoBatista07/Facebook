package exceptions;

public class UserCanNotComentPostException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 112218804686830108L;
	
	private String idUserComment;
	
	public UserCanNotComentPostException(String idUserComment) {
		super();
		
		this.idUserComment = idUserComment;
	}
	
	public String getIdUserComment() {
		return idUserComment;
	}
	
}
