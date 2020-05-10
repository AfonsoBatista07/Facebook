package system;

import java.util.LinkedList;

import exceptions.InadequateStanceException;
import exceptions.InvalidCommentStanceException;
import exceptions.InvalidFanaticismListException;
import exceptions.InvalidHashtagsListException;
import exceptions.UnknownUserKindException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserCanNotComentPostException;
import exceptions.UserDoesNotExistException;
import exceptions.UserHasNoPostsException;
import exceptions.UserNoAccessToPostException;
import exceptions.UsersAlreadyFriendsException;

public interface FakeSystem {

	boolean isFanatic(String kind);
	
	boolean unknownUserKind(String kind);
	
	boolean userExists(String user);
	
	boolean repeatedFanaticism(LinkedList<String> sequence);
	
	void addUser(String type, String userId, int numberFanaticisms, LinkedList<String> sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment) throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotComentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistException, UsersAlreadyFriendsException;
	
	void newPost(String userId, int hashtagsNumber, String[] hashtags, String truthfulness, String message) throws UserAlreadyExistsException, InvalidHashtagsListException, InadequateStanceException;
	
	int getNumberFriends(String userId);
	
	String getPostId(String userId);
}
