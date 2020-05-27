package post;

/**
 * Implements a Commment.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class CommentClass implements Comment{
	private String userId, stance, comment;
	private Post post;

	private static final String POSITIVE = "positive";
	
	/**
	 * Constructor of the CommentClass, initializes variables.
	 * @param userId - Author of the Comment
	 * @param stance - Stance of the Comment ( positive or fake )
	 * @param comment - Message of the Comment
	 * @param post - Post where the Comment was posted
	 */
	public CommentClass(String userId, String stance, String comment, Post post) {
		this.userId = userId;
		this.stance = stance;
		this.comment = comment;
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public String getStance() {
		return stance;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Post getPost() {
		return post;
	}

	public boolean isPositive() {
		return stance.equalsIgnoreCase(POSITIVE);
	}

}
