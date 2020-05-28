package post;

import java.util.Iterator;

/**
 * Interface of the PostClass.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface Post {
	
	/**
	 * @return The Posts Message.
	 */
	String getMessage();
	
	/**
	 * @return Id of the Author of the Post.
	 */
	String getAuthorId();
	
	/**
	 * @return Type of the Post ( Honest or Fake ).
	 */
	String getType();
	
	/**
	 * @return Id of the Post.
	 */
	int getIdPost();
	
	/**
	 * @return Number of Comments on the Post.
	 */
	int getNumComments();
	
	/**
	 * @return Number of HashTags.
	 */
	int getNumHashTags();
	
	/**
	 * Verifies if the Post is Honest.
	 * @return True if the Post is Honest. False if not.
	 */
	boolean isHonest();
	
	/**
	 * Adds comment to the Posts Comment list.
	 * @param comment - Comment
	 */
	void newComment(Comment comment);
	
	/**
	 * @return Iterator with the Posts HashTags.
	 */
	Iterator<String> getHashTags();
	
	/**
	 * @return Iterator with the Posts Comments.
	 */
	Iterator<Comment> readPost();
	
}
