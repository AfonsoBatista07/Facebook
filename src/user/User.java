package user;

public interface User {

	String getId();
	String getType();
	void newPost(String[] hashtags, String type, String message);
	void newComment(String idComment, String idPost, String stance, String comment);
	void addFriend(User user);
	
}
