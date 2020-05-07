package system;

import exceptions.InadequateStanceException;
import exceptions.InvalidCommentStanceException;
import exceptions.InvalidFanaticismListException;
import exceptions.InvalidHashtagsListException;
import exceptions.UnknownUserKindException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserCanNotComentPostException;
import exceptions.UserDoesNotExistExeption;
import exceptions.UserHasNoPostsException;
import exceptions.UserNoAccessToPostException;
import exceptions.UsersAlreadyFriendsException;

public interface FakeSystem {

	boolean isFanatic(String type);
	
	void addUser(String type, String userId, int numberFanaticisms, String[] sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment) throws UserDoesNotExistExeption, UserHasNoPostsException, UserCanNotComentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistExeption, UsersAlreadyFriendsException;
	
	void newPost(String userId, int hashtagsNumber, String[] hashtags, String truthfulness, String message) throws UserAlreadyExistsException, InvalidHashtagsListException, InadequateStanceException;
	
	int getNumberFriends(String userId);
	
	String getPostId(String userId);
}
