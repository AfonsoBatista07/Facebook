package post;

/**
 * Interface of the CommentClass.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface Comment {

	/**
	 * @return Message of the Comment.
	 */
	String getComment();
	
	/**
	 * @return Stance of the Comment.
	 */
	String getStance();
	
	/**
	 * @return Author of the Comment.
	 */
	String getUserId();
	
	/**
	 * Verifies if the Comment is Positive.
	 * @return True if the Comment if Positive. False if not.
	 */
	boolean isPositive();
	
	/**
	 * @return Post where the Comment was posted.
	 */
	Post getPost();
	
}
