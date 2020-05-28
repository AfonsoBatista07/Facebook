package system;

import user.*;
import post.*;
import system.exceptions.*;
import java.util.*;

/**
 * FakeSystemClass processes all the information from the other classes.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class FakeSystemClass implements FakeSystem {
	
	private List<User> topLiars;
	private SortedMap<String, User> users;
	private Map<String, LinkedList<Post>> posts;
	private Map<String, SortedSet<String>> fanaticsBytopic;   
	private Post popularPost;
	private User topPoster, responsive, shameless;
	
	/**
	 * Constructor of FakeSystemClass, initializes variables.
	 */
	public FakeSystemClass() {
		topLiars = new LinkedList<User>();
		users = new TreeMap<String, User>();
		posts = new HashMap<String, LinkedList<Post>>();
		fanaticsBytopic = new HashMap<String, SortedSet<String>>();
	}
	
	public void addUser(String kind, String userId, int numFanaticisms, List<String> sequence) {
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
	
	public void addComment(String idUserComment, String idUserAuthor, int postId, String stance, String comment) {
		User userComment = getUser(idUserComment), userAuthor = getUser(idUserAuthor);
		Post post = userAuthor.getPost(postId);
		
		if(!userComment.hasAccess(post)) throw new UserNoAccessToPostException();
		
		Comment cmt = new CommentClass(idUserComment, stance, comment, post);
		userComment.newComment(cmt);
		
		if(morePopular(post)) popularPost = post;
		if(responsive(userComment)) responsive = userComment;
		if(shameless(userComment)) shameless = userComment;
	}
	
	public void addFriend(String firstUserId, String secondUserId) {
		User firstUser = getUser(firstUserId), secondUser = getUser(secondUserId);
		if(firstUser.equals(secondUser)) throw new UserCanNotBeTheSameException();
		
		firstUser.addFriend(secondUser); secondUser.addFriend(firstUser);
	}
	
	public void newPost(String userId, int hashtagsNumber, List<String> hashtags, String truthfulness, String message) {
		User user = getUser(userId);
		if(hashtagsNumber < 0 || repeatedTags(hashtagsNumber, hashtags)) throw new InvalidHashtagsListException();
		Post post = new PostClass(userId, getNumPosts(userId)+1, hashtagsNumber, hashtags, truthfulness, message);
		user.newPost(post);
		user.sharePost(post);
		addPostsByTopic(post);
		
		if(topPoster(user)) topPoster = user;
		if(responsive(user)) responsive = user;
		if(shameless(user)) shameless = user;
	}
	
	public boolean isFanatic(String kind) {
		return kind.equals(User.FANATIC);
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
		if(numberOfPosts < 1) throw new InvalidNumberOfPostsException();         
		List<Post> list = posts.get(hashtag);
		if(list == null) throw new UnKnownTopicException();
		Collections.sort(list, new ComparatorSortPosts());
		return list.iterator();   
	}
	
	/**
	 * Adds a sorted set to the map fanaticsBytopic and then a Fanatic user to the sorted set.
	 * @param user - Fanatic user.
	 */
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
	
	/**
	 * Adds a linked list to the map posts and then a post to the linked list.
	 * @param post - Post.
	 */
	private void addPostsByTopic(Post post) {
		Iterator<String> it = post.getHashTags();
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
	
	/**
	 * @param post - Post.
	 * @return true if post post have more comments than the popularPost, if there is a tie,
	 * true if post is more recent than the popularPost.
	 */
	private boolean morePopular(Post post) {
		ComparatorPopularPost comparator = new ComparatorPopularPost();
		return comparator.compare(post, popularPost)==1;
	}
	
	/**
	 * @param user - User
	 * @return true if user have more posts than the topPoster, if there is a tie,
	 * true if user have wrote more comments than the topPoster, if there is still a tie,
	 * true if user a user id alphabetically bigger than topPoster.
	 */
	private boolean topPoster(User user) {
		ComparatorTopPoster comparator = new ComparatorTopPoster();
		return comparator.compare(user, topPoster)==1; 
	}
	
	/**
	 * @param user - User.
	 * @return true if user have a higher percentage of commented posts than the responsive,
	 * if there is a draw, true user have a user id alphabetically bigger than responsive.
	 */
	private boolean responsive(User user) {
		ComparatorResponsive comparator = new ComparatorResponsive();
		return comparator.compare(user, responsive)==1;
	}
	
	/**
	 * @param numTags - Number of hashTags.
	 * @param tags - HashTags.
	 * @return true if there are any repeatedTags.
	 */
	private boolean repeatedTags(int numTags, List<String> tags) {
		for (int i = 0; i < numTags-1; i++) {
			for(int j = i+1; j < numTags; j++) {
				if(tags.get(i).equals(tags.get(j))) return true;
			}
		}
		return false;
	}
	
	/**
	 * @param user - User.
	 * @return true if user have more lies than the shameless or if are the same,
	 * true if user have lower sum of posts and comments, if the ties still remain,
	 * true if user have a user id alphabetically bigger than shameless
	 */
	private boolean shameless(User user) {
		if(shameless == null || user.getNumberOfLies() > shameless.getNumberOfLies()) {
			topLiars.clear();
			return true;
		}
		if ( user.getNumberOfLies() == shameless.getNumberOfLies()) {
			if(!topLiars.contains(user)) topLiars.add(user);
			if(!topLiars.contains(shameless)) topLiars.add(shameless);
			int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
			for( User liar : topLiars) {
				int liarSum = liar.getTotalNumberComments() + liar.getNumberPosts();
				if(liarSum < shamelessSum) shameless = liar;
			}
			int userSum = user.getTotalNumberComments() + user.getNumberPosts();
			if(userSum < shamelessSum) return true;
			if(userSum == shamelessSum && user.getId().compareTo(shameless.getId()) > 0) return true;
		}
		return false;
	}
	
	/**
	 * @param userId - Id of the User.
	 * @return true if the user exits.
	 */
	private boolean userExists(String userId) {
		return users.get(userId)!=null;
	}
}
