package system;
import user.User;

public interface UserCollection {

	void newUser(String type, String id, String[] fanaticism);
	void newPost(String id, String[] hashtags, String type, String message);
	void newComment(String idComment, String idAuthor, String idPost, String stance, String comment);
	void addFriend(String firstId, String secondId);
	User getUser(String id);
	int size();
	
}
