package system;

import exceptions.*;
import user.*;
import post.*;
import java.util.*;

public class FakeSystemClass implements FakeSystem {
	
	private SortedMap<String, User> users;
	private Map<String, SortedMap<String,User>> fanaticsBytopic;
	private Map<String, SortedSet<Post>> posts;
	
	
	public FakeSystemClass() {
		users = new TreeMap<String, User>();
		posts = new HashMap<String, SortedSet<Post>>();
		fanaticsBytopic = new HashMap<String, SortedMap<String, User>>();
	}
	
	public boolean isFanatic(String kind) {
		return kind.equals(User.FANATIC);
	}
	
	public void addUser(String kind, String userId, int numFanaticisms, LinkedList<String> sequence) { //Mudar possivelments a linkedlist to arrayList
		User user=null;      // NULL ??
		
		switch(kind) {
			case User.LIAR:
				user = new LiarClass(userId); 
				break;
			case User.NAIVE:
				user = new NaiveClass(userId);
				break;
			case User.SELF_CENTERED:
				user = new SelfCenteredClass(userId);
				break;
			case User.FANATIC:
				if(repeatedTags(numFanaticisms, sequence)) throw new InvalidFanaticismListException();   
				user = new FanaticClass(userId, numFanaticisms, sequence);
				addFanaticsByTopic(numFanaticisms, sequence, user);
				break;
			default:
				throw new UnknownUserKindException();
		}
		if(userExists(userId)) throw new UserAlreadyExistsException(userId);    //OBJECTOS CRIADOS A TOA ?
		users.put(userId, user);
	}
	
	private void addFanaticsByTopic (int numFanaticisms, LinkedList<String> sequence, User user) {
		for(int i = 0; i < numFanaticisms; i++) {
			String topic = sequence.get(1 + 2*i);							//Maybe change the whole cicle
			SortedMap<String, User> map = fanaticsBytopic.get(topic);
			if (map == null) {
				map = new TreeMap<String, User>();
				fanaticsBytopic.put(topic, map);
			}
			map.put(user.getId(), user);
		}
	}
	
	public void addFriend(String firstUserId, String secondUserId) {
		User firstUser = getUser(firstUserId), secondUser = getUser(secondUserId);
		if(firstUser.equals(secondUser)) throw new UserCanNotBeTheSameException();
		firstUser.addFriend(secondUser); secondUser.addFriend(firstUser);
	}
	
	public void newPost(String userId, int hashtagsNumber, LinkedList<String> hashtags, String truthfulness, String message) {
		User user = getUser(userId);
		if(hashtagsNumber < 0 || repeatedTags(hashtagsNumber, hashtags)) throw new InvalidHashtagsListException();
		Post post = new PostClass(userId, getPostId(userId)+1, hashtagsNumber, hashtags, truthfulness, message);
		user.newPost(post);
		addPostsByTopic(hashtagsNumber, hashtags, post);
		sharePost(post, user);
	}
	
	private void addPostsByTopic(int hashtagsNumber, LinkedList<String> hashtags, Post post) {
		Iterator<String> it = hashtags.iterator();
		while(it.hasNext()) {
			String tag = it.next();
			SortedSet<Post> set = posts.get(tag);
			if (set == null) {
				set = new TreeSet<Post>(new SortPosts());
				posts.put(tag, set);
			}
			set.add(post);
		}
	}

	public void addComment(String idUserComment, String idUserAuthor, int idPost, String stance, String comment) {
		User userComment = getUser(idUserComment), userAuthor = getUser(idUserAuthor);
		if(!hasFriend(userComment, userAuthor) && !idUserComment.equals(idUserAuthor)) throw new UserNoAccessToPostException();
		if(!hasPost(userAuthor, idPost)) throw new UserHasNoPostsException();
		Comment cmt = new CommentClass(idUserComment, stance, comment, userAuthor.getPost(idPost));
		userComment.newComment(cmt);

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
		User user = users.get(userId);
		if(user==null) throw new UserDoesNotExistException(userId);
		return user;
	}

	public int getNumberFriends(String userId) {
		return getUser(userId).getNumberFriends();
	}

	public int getPostId(String userId) {
		return getUser(userId).getNumberPosts();
	}
	
	public boolean userExists(String userId) {
		return users.get(userId)!=null;
	}
	
	public Post getPopularPost() {
		return null;                    // TODO
	}
	
	public User getTopPoster() {
		return null;                    // TODO
	}
	
	public User getResponsive() {
		return null;                    // TODO
	}
	
	public Liar getShameless() {
		return null;                    // TODO
	}

	public Iterator<User> listUsers() throws NoUsersException{
		Iterator<User> it = users.values().iterator();
		
		if(!it.hasNext()) throw new NoUsersException();
		
		return it;
	}
	
	public Iterator<User> listFriends(String userId) {
		User user = getUser(userId);
		Iterator<User> it = user.getFriendIterator();
		
		if(!it.hasNext()) throw new NoFriendsException();
		
		return it;
	}
	
	public Iterator<Post> listUserPosts(String userId) {
		User user = getUser(userId);
		Iterator<Post> it = user.getMyPostsIterator();
		
		if(!it.hasNext()) throw new NoPostsException();
		
		return it;
	}
	
	public Post getPost(String userId, int postId) {
		User user = getUser(userId);
		if(!hasPost(user, postId)) throw new UserHasNoPostsException();
		
		return getUser(userId).getPost(postId);
	}

	public Iterator<Comment> readPost(Post post) {
		User user = getUser(post.getAuthorId());
		
		Iterator<Comment> it = user.readPost(post);
		
		if(!it.hasNext()) throw new NoCommentsException();
		
		return it;
	}
	
	public Iterator<Comment> listCommentByUser(String userId, String hashtag) {
		User user = getUser(userId);
		//Iterator<Comment> it = user.getListCommentByUser(hashtag);
		//if(!it.hasNext()) throw new NoCommentsException();
		return user.getListCommentByUser(hashtag);
	}
	
	public Iterator<User> listFanaticsByTopic(String hashtag) {
		SortedMap<String, User> map = fanaticsBytopic.get(hashtag);
		if( map == null) throw new UnknownFanaticismException();
		return fanaticsBytopic.get(hashtag).values().iterator();
	}
	
	public Iterator<Post> listTopicPosts(int numberOfPosts, String hashtag) {
		if(numberOfPosts < 1) throw new InvalidNumberOfPostsException();
		SortedSet<Post> set = posts.get(hashtag);
		if( set == null) throw new UnKnownTopicException();
		return set.iterator();   
	}
}
