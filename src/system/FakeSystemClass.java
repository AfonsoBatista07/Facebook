package system;

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

import java.util.*;

import user.*;
import post.Post;

public class FakeSystemClass implements FakeSystem {
	
	
	
	private static final int DEFAULT_QUANTITY = 500;
	
	private SortedMap<String, User> users;
	
	private Map<String, Post> posts;
	
	public FakeSystemClass() {
		users = new TreeMap<String, User>();
		
		posts = new HashMap<String, Post>(DEFAULT_QUANTITY);
	}

	public boolean isFanatic(String kind) {
		return false;
	}
	
	public boolean isLiar(String kind) {
		return false;
	}
	
	public boolean isNaive(String kind) {
		return false;
	}
	
	public void addUser(String kind, String userId, int numFanaticisms, LinkedList<String> sequence)
			throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException {
		if(unknownUserKind(kind)) throw new UnknownUserKindException();
		if(userExists(userId)) throw new UserAlreadyExistsException(userId);
		if(repeatedFanaticism(sequence)) throw new InvalidFanaticismListException();
		
		User user;
		if(isFanatic(kind)) user = new FanaticClass(userId, numFanaticisms, sequence, sequence);  // SEQUENCE ERRADA
		else if(isLiar(kind)) user = new LiarClass(userId);
		else if(isNaive(kind)) user = new NaiveClass(userId);
		else user = new SelfCenteredClass(userId);
		
		users.put(userId, user);
	}

	public void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment)
			throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotComentPostException,
			UserNoAccessToPostException, InvalidCommentStanceException {

	}

	public void addFriend(String firstUserId, String secondUserId)
			throws UserDoesNotExistException, UsersAlreadyFriendsException {

	}

	public void newPost(String userId, int hashtagsNumber, String[] hashtags, String truthfulness, String message)
			throws UserAlreadyExistsException, InvalidHashtagsListException, InadequateStanceException {

	}

	public int getNumberFriends(String userId) {
		return 0;
	}

	public String getPostId(String userId) {
		return null;
	}
	
	public boolean userExists(String userId) {
		User user = users.get(userId);
		return user!=null;
	}
	
	public boolean unknownUserKind(String kind) {
		return kind.equalsIgnoreCase(User.FANATIC) ||
			   kind.equalsIgnoreCase(User.LIAR) ||                //BETTER WAY
			   kind.equalsIgnoreCase(User.NAIVE) ||
			   kind.equalsIgnoreCase(User.SELF_CENTERED);
	}
	
	public boolean repeatedFanaticism(LinkedList<String> sequence) {          //FAZERRRRR !
		return true;
	}

}
