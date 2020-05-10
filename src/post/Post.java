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
	Iterator<String> getHashTags();
	
}
