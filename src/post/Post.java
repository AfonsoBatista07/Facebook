package post;

import java.util.Iterator;

public interface Post {
	
	String getMessage();
	String getAuthorId();
	String getType();
	int getIdPost();
	int getNumHashTags();
	int getNumComments();
	boolean isHonest();
	boolean hasComment(String userId);
	void newComment(Comment comment);
	Iterator<String> getHashTags();
	Iterator<Comment> readPost();
	
}
