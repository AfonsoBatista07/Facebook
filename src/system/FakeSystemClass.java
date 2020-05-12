package system;

import exceptions.*;
import user.*;
import post.*;
import java.util.*;

public class FakeSystemClass implements FakeSystem {
	
	private SortedMap<String, User> users;
	
	public FakeSystemClass() {
		users = new TreeMap<String, User>();
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
		
		if(isSelfCentered(kind)) user = new SelfCenteredClass(userId);
		else if(isLiar(kind)) user = new LiarClass(userId); 
		else if(isNaive(kind)) user = new NaiveClass(userId); 
		else {
			if(repeatedTags(numFanaticisms, sequence)) throw new InvalidFanaticismListException();     //NAO FUNCIONA
			user = new FanaticClass(userId, numFanaticisms, sequence);
		}
		users.put(userId, user);
	}
	
	public void addFriend(String firstUserId, String secondUserId) {
		User firstUser = getUser(firstUserId), secondUser = getUser(secondUserId);
		
		
		if(!userExists(firstUser)) throw new UserDoesNotExistException(firstUserId);
		if(!userExists(secondUser)) throw new UserDoesNotExistException(secondUserId);
		
		firstUser.addFriend(secondUser); secondUser.addFriend(firstUser);
	}
	
	public void newPost(String userId, int hashtagsNumber, LinkedList<String> hashtags, String truthfulness, String message) {
		User user = getUser(userId);
		if(!userExists(user)) throw new UserDoesNotExistException(userId);
		if(hashtagsNumber <= 0 || repeatedTags(hashtagsNumber, hashtags)) throw new InvalidHashtagsListException();
		Post post = new PostClass(userId, getPostId(userId)+1, hashtagsNumber, hashtags, truthfulness, message);
		user.newPost(post);
		sharePost(post, user);
	}

	public void addComment(String idUserComment, String idUserAuthor, int idPost, String stance, String comment) {
		User userComment = getUser(idUserComment), userAuthor = getUser(idUserAuthor);
		if(!userExists(userComment)) throw new UserDoesNotExistException(idUserComment);
		if(!userExists(userAuthor)) throw new UserDoesNotExistException(idUserAuthor);
		if(!(hasFriend(userComment, userAuthor) || userComment.equals(userComment))) throw new UserNoAccessToPostException();
		if(!hasPost(userAuthor, idPost)) throw new UserHasNoPostsException();
		Comment cmt = new CommentClass(idUserComment, stance, comment);
		userAuthor.newComment(idPost, cmt);

	}
	
	private boolean hasPost(User user, int idPost) {
		return user.hasPost(idPost);
	}
	
	private boolean hasFriend(User firstUser, User secondUser) {
		return firstUser.hasFriend(secondUser);
	}
	
	private boolean repeatedTags(int numTags, LinkedList<String> tags) {
		for (int i = 0; i < numTags-1; i++) {
			for(int j = i+1; j < numTags; j++) {
				if(tags.get(i).equals(tags.get(j))) return true;
			}
		}
		return false;
	}
	
	private void sharePost(Post post, User user) {
		user.sharePost(post);
	}
	
	private User getUser(String userId) {
		return users.get(userId);
	}

	public int getNumberFriends(String userId) {
		return getUser(userId).getNumberFriends();
	}

	public int getPostId(String userId) {
		return getUser(userId).getNumberPosts();
	}
	
	public boolean userExists(User user) {
		return user!=null;
	}
	
	public boolean unknownUserKind(String kind) {
		return isFanatic(kind) ||
			   isLiar(kind) ||          
			   isNaive(kind) ||
			   isSelfCentered(kind);
	}

	public Iterator<User> listUsers() throws NoUsersException{
		Iterator<User> it = users.values().iterator();
		
		if(!it.hasNext()) throw new NoUsersException();
		
		return it;
	}
	
	public Iterator<User> listFriends(String userId) {
		User user = getUser(userId);
		if(!userExists(user)) throw new UserDoesNotExistException(userId);
		
		Iterator<User> it = user.getFriendIterator();
		
		if(!it.hasNext()) throw new NoFriendsException();
		
		return it;
	}
	
	public Iterator<Post> listUserPosts(String userId) {
		User user = getUser(userId);
		if(!userExists(user)) throw new UserDoesNotExistException(userId);
		
		Iterator<Post> it = user.getMyPostsIterator();
		
		if(!it.hasNext()) throw new NoPostsException();
		
		return it;
	}
	
	public Post getPost(String userId, int postId) {
		User user = getUser(userId);
		
		if(!userExists(user)) throw new UserDoesNotExistException(userId);
		if(!hasPost(user, postId)) throw new UserHasNoPostsException();
		
		return getUser(userId).getPost(postId);
	}

	public Iterator<Comment> readPost(Post post) {
		User user = getUser(post.getAuthorId());
		
		Iterator<Comment> it = user.readPost(post);
		
		if(!it.hasNext()) throw new NoCommentsException();
		
		return it;
	}

}
