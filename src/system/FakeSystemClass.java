package system;

import exceptions.*;

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
		return kind.equalsIgnoreCase(User.FANATIC);
	}
	
	public boolean isLiar(String kind) { 
		return kind.equalsIgnoreCase(User.LIAR); 
	} 
	 
	public boolean isNaive(String kind) { 
		return kind.equalsIgnoreCase(User.NAIVE); 
	}
	
	public boolean isSelfCentered(String kind) {
		return kind.equalsIgnoreCase(User.SELF_CENTERED);
	}
	
	public void addUser(String kind, String userId, int numFanaticisms, LinkedList<String> sequence) {
		User user = getUser(userId);
		if(!unknownUserKind(kind)) throw new UnknownUserKindException(); 
		if(userExists(user)) throw new UserAlreadyExistsException(userId); 
		
		if(isFanatic(kind)) user = new SelfCenteredClass(userId);
		else if(isLiar(kind)) user = new LiarClass(userId); 
		else if(isNaive(kind)) user = new NaiveClass(userId); 
		else {
			if(repeatedFanaticism(sequence)) throw new InvalidFanaticismListException();     //NAO FUNCIONA
			user = new FanaticClass(userId, numFanaticisms, sequence, sequence);
		}
		users.put(userId, user);
	}
	
	public void addFriend(String firstUserId, String secondUserId) {
		User firstUser = getUser(firstUserId), secondUser = getUser(secondUserId);
		
		
		if(!userExists(firstUser)) throw new UserDoesNotExistException(firstUserId);
		if(!userExists(secondUser)) throw new UserDoesNotExistException(secondUserId);
		if(firstUser.hasFriend(secondUser)) throw new UsersAlreadyFriendsException();
		
		firstUser.addFriend(secondUser); secondUser.addFriend(firstUser);
	}

	public void addComment(String idUserComment, String idUserAuthor, String idPost, String stance, String comment)
			throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotComentPostException,
			UserNoAccessToPostException, InvalidCommentStanceException {

	}

	public void newPost(String userId, int hashtagsNumber, String[] hashtags, String truthfulness, String message)
			throws UserAlreadyExistsException, InvalidHashtagsListException, InadequateStanceException {

	}
	
	public User getUser(String userId) {
		return users.get(userId);
	}

	public int getNumberFriends(String userId) {
		return getUser(userId).getNumberFriends();
	}

	public int getPostId(String userId) {
		return getUser(userId).getNumberPosts()+1;
	}
	
	public boolean userExists(User user) {
		return user!=null;
	}
	
	public boolean repeatedFanaticism(LinkedList<String> sequence) {          //FAZERRRRR !
		return true;
	}
	
	public boolean unknownUserKind(String kind) { 
		return isFanatic(kind) || 
			   isLiar(kind) ||                //BETTER WAY 
			   isNaive(kind) || 
			   isSelfCentered(kind); 
	} 
	
	public Iterator<User> listUsers() throws NoUsersException{
		Iterator<User> it = users.values().iterator();
		
		if(!it.hasNext()) throw new NoUsersException();
		
		return it;
	}
	
	public Iterator<User> listFriends(String userId) throws UserDoesNotExistException, NoFriendsException {
		User user = getUser(userId);
		if(!userExists(user)) throw new UserDoesNotExistException(userId);
		Iterator<User> it = getUser(userId).getFriendIterator();
		if(!it.hasNext()) throw new NoFriendsException();
		return it;
	}

}
