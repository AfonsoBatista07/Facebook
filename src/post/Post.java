package post;

import java.util.Iterator;

public interface Post {
	
	String getMessage();
	String getAuthorId();
	String getType();
	int getIdPost();
	int getNumHashTags();
	boolean isHonest();
	Iterator<String> getHashTags();
	
}
