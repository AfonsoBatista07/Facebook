package post;

import java.util.Iterator;
import java.util.LinkedList;

public class PostClass implements Post{
	private String message, type, idUser;
	private int numHashTags, idPost;
	private LinkedList<String> hashTags;
	private LinkedList<Comment> comments;
	
	public PostClass(String idUser, int idPost, int numHashTags, LinkedList<String> hashTags, String type, String message) {
		this.idUser = idUser;
		this.idPost = idPost;
		this.numHashTags = numHashTags;
		this.hashTags = hashTags;
		this.type = type;
		this.message = message;
		
		comments = new LinkedList<Comment>();
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
	
	public int getNumHashTags() {
		return numHashTags;
	}
	
	public int getNumComments() {
		return comments.size();
	}
	
	public int getIdPost() {
		return idPost;
	}
	
	public Iterator<String> getHashTags() {
		return hashTags.iterator();
	}
	
	public boolean isHonest() {
		return type.equalsIgnoreCase("honest");          //MUDAR ISTO
	}

}