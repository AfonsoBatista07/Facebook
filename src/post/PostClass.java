package post;

import java.util.Iterator;
import java.util.LinkedList;

public class PostClass implements Post{
	private String message, type, idUser;
	private int numHashTags, idPost;
	private LinkedList<String> hashTags; // Mapa de HashTags?
	private LinkedList<Comment> comments; 
	
	private static final String HONEST = "honest";
	
	public PostClass(String idUser, int idPost, int numHashTags, LinkedList<String> hashTags, String type, String message) {
		this.idUser = idUser;
		this.idPost = idPost;
		this.numHashTags = numHashTags;
		this.hashTags = hashTags;
		this.type = type;
		this.message = message;
		
		comments = new LinkedList<Comment>();
	}
	
	public void newComment(Comment comment) {
		comments.add(comment);
	}
	
	public boolean hasComment(String userId) {
		for(Comment comment: comments) {
			if(comment.getUserId().equals(userId));                //BETTER WAY?
				return true;
		}
		return false;
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
	
	public Iterator<Comment> readPost() {
		return comments.iterator();
	}
	
	public boolean isHonest() {
		return type.equalsIgnoreCase(HONEST);          
	}

}