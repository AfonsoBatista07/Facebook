package post;

import java.util.LinkedList;

public class PostClass implements Post{
	private String message, type, idUser;
	private int numHashTags, idPost;
	private String[] hashTags;
	private LinkedList<Comment> comments;
	
	public PostClass(String idUser, int idPost, int numHashTags, String[] hashTags, String type, String message) {
		this.idUser = idUser;
		this.numHashTags = numHashTags;
		this.hashTags = hashTags;
		this.type = type;
		this.message = message;
	}
	
	public void newComment(String userId, String stance, String comment) {
		comments.addLast(new CommentClass(userId, stance, comment));
	}
	
	public String getMessage() {
		return message;
	}

	public String getAuthorId() {
		return idUser;
	}

	public String getType() {
		return type;
	}
	
	public boolean isHonest() {
		return type.equalsIgnoreCase("honest");
	}

}
