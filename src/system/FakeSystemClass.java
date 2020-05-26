package system;

import user.*;
import post.*;
import system.exceptions.*;

import java.util.*;

public class FakeSystemClass implements FakeSystem {
	
	private SortedMap<String, User> users;
	private Map<String, LinkedList<Post>> posts;
	private Map<String, SortedSet<String>> fanaticsBytopic;   
	private Post popularPost;
	private User topPoster, responsive, shameless;
	
	public FakeSystemClass() {
		users = new TreeMap<String, User>();
		posts = new HashMap<String, LinkedList<Post>>();
		fanaticsBytopic = new HashMap<String, SortedSet<String>>();
	}
	
	public void addUser(String kind, String userId, int numFanaticisms, LinkedList<String> sequence) { //Mudar possivelments a linkedlist to arrayList
		User user;    
		
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
				addFanaticsByTopic((Fanatic) user);
				break;
			default:
				
				throw new UnknownUserKindException();
		}
		if(userExists(userId)) throw new UserAlreadyExistsException(userId);  
		users.put(userId, user);
	}
	
	private void addFanaticsByTopic (Fanatic user) {
		Iterator<String> fanaticisms = user.getFanaticisms();
		while(fanaticisms.hasNext()) {
			String topic = fanaticisms.next();
			SortedSet<String> map = fanaticsBytopic.get(topic); 
			if (map == null) { 
				map = new TreeSet<String>(); 
				fanaticsBytopic.put(topic, map); 
			} 
			map.add(user.getId());
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
		Post post = new PostClass(userId, getNumPosts(userId)+1, hashtagsNumber, hashtags, truthfulness, message);
		user.newPost(post);
		addPostsByTopic(hashtagsNumber, hashtags, post);
		user.sharePost(post);
		
		if(topPoster(user)) topPoster = user;
		if(responsive(user)) responsive = user;
		if(shameless(user)) shameless = user;
	}
	
	private void addPostsByTopic(int hashtagsNumber, LinkedList<String> hashtags, Post post) {
		Iterator<String> it = hashtags.iterator();
		while(it.hasNext()) {
			String tag = it.next();
			LinkedList<Post> list = posts.get(tag);
			if (list == null) {
				list = new LinkedList<Post>();
				posts.put(tag, list);
			}
			list.add(post);
		}
	}

	public void addComment(String idUserComment, String idUserAuthor, int idPost, String stance, String comment) {
		User userComment = getUser(idUserComment), userAuthor = getUser(idUserAuthor);
		Post post = userAuthor.getPost(idPost);
		
		if(!userComment.hasAccess(post)) throw new UserNoAccessToPostException();
		
		Comment cmt = new CommentClass(idUserComment, stance, comment, post); // Objecto flutuante???
		userComment.newComment(cmt);
		
		if(morePopular(post)) popularPost = post;
		if(responsive(userComment)) responsive = userComment;
		if(shameless(userComment)) shameless = userComment;
	}
	
	private boolean responsive(User user) {
		ComparatorResponsive comparator = new ComparatorResponsive();
		return comparator.compare(user, responsive)==1;
	}
	
	private boolean morePopular(Post post) {
		ComparatorPopularPost comparator = new ComparatorPopularPost();
		return comparator.compare(post, popularPost)==1;
	}
	                                                                                                      
	private boolean topPoster(User user) {
		ComparatorTopPoster comparator = new ComparatorTopPoster();
		return comparator.compare(user, topPoster)==1; 
	}
	
	private boolean shameless(User user) {
		ComparatorShameless comparator = new ComparatorShameless();
		return comparator.compare(user, shameless) == 1;
	}
	
	public boolean isFanatic(String kind) {
		return kind.equals(User.FANATIC);
	}
	
	private boolean repeatedTags(int numTags, LinkedList<String> tags) {
		for (int i = 0; i < numTags-1; i++) {
			for(int j = i+1; j < numTags; j++) {
				if(tags.get(i).equals(tags.get(j))) return true;
			}
		}
		return false;
	}
	
	public boolean userExists(String userId) {
		return users.get(userId)!=null;
	}
	
	public int getNumFriends(String userId) {
		return getUser(userId).getNumberFriends();
	}

	public int getNumPosts(String userId) {
		return getUser(userId).getNumberPosts();
	}
	
	private User getUser(String userId) {
		User user = users.get(userId);
		if(user==null) throw new UserDoesNotExistException(userId);
		return user;
	}
	
	public Post getPost(String userId, int postId) {
		return getUser(userId).getPost(postId);
	}
	
	public Post getPopularPost() {
		if(popularPost == null) throw new NoKingPopularPostException();
		return popularPost;
	}
	
	public User getTopPoster() {
		if(topPoster == null) throw new NoKingPostersException();
		return topPoster;                    
	}
	
	public User getResponsive() {
		if(responsive == null || responsive.getTotalNumberComments() == 0) throw new NoKingOfResponsivenessException();
		return responsive;                 
	}
	
	public User getShameless() {
		if(shameless == null || shameless.getNumberOfLies() == 0) throw new NoKingOfLiarsException();
		return shameless;                
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

	public Iterator<Comment> readPost(Post post) {
		User user = getUser(post.getAuthorId());
		
		Iterator<Comment> it = user.readPost(post);
		
		if(!it.hasNext()) throw new NoCommentsException();
		
		return it;
	}
	
	public Iterator<Comment> listCommentByUser(String userId, String hashtag) {                 
		return getUser(userId).getListCommentByUser(hashtag);
	}
	
	public Iterator<String> listFanaticsByTopic(String hashtag) {
		SortedSet<String> map = fanaticsBytopic.get(hashtag);
		if(map == null) throw new UnknownFanaticismException();
		return map.iterator();
	}
	
	public Iterator<Post> listTopicPosts(int numberOfPosts, String hashtag) {
		if(numberOfPosts < 1) throw new InvalidNumberOfPostsException();           //Voltar a ver
		LinkedList<Post> list = posts.get(hashtag);
		if(list == null) throw new UnKnownTopicException();
		Collections.sort(list, new ComparatorSortPosts());
		return list.iterator();   
	}
}
