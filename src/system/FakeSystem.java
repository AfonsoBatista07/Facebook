package system;

import java.util.Iterator;
import java.util.LinkedList;

import exceptions.*;
import post.Post;
import user.User;

public interface FakeSystem {

	boolean isFanatic(String kind);
	
	boolean isLiar(String kind);
	
	boolean isNaive(String kind);
	
	boolean isSelfCentered(String kind);
	
	boolean userExists(User user);
	
	void addUser(String type, String userId, int numberFanaticisms, LinkedList<String> sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment) throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotComentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistException, UsersAlreadyFriendsException;
	
	void newPost(String userId, int hashtagsNumber, LinkedList<String> hashtags, String truthfulness, String message);
	
	int getNumberFriends(String userId);
	
	int getPostId(String userId);
	
	Iterator<User> listUsers() throws NoUsersException;
	
	Iterator<User> listFriends(String userId) throws UserDoesNotExistException, NoFriendsException;
	
	Iterator<Post> listUserPosts(String userId) throws UserDoesNotExistException, NoPostsException; 
}
