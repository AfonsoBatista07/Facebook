package post;

public class CommentClass implements Comment{
	private String userId, stance, comment;

	public CommentClass(String userId, String stance, String comment) {
		this.userId = userId;
		this.stance = stance;
		this.comment = comment;
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

	public boolean isPositive() {
		return stance.equalsIgnoreCase("positive");
	}

}
