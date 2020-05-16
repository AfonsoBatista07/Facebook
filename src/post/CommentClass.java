package post;

public class CommentClass implements Comment{
	private String userId, stance, comment;
	private Post post;

	private static final String POSITIVE = "positive";
	
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
