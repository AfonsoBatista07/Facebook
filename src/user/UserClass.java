package user;
import java.util.*;

import post.Comment;
import post.Post;
import user.exceptions.*;

/**
 * Implements a User.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public abstract class UserClass implements User {
	
	private String id, kind;
	private SortedMap<String, User> friends;
	private List<Post> myPosts; 
	private Set<Post> myFeed;
	private Map<String,LinkedList<Comment>> commentsByTag;
	private Set<Post> commentedPost;
	private int numComments, numOfLies;
	
	/**
	 * Constructor of UserClass, initializes variables.
	 * @param id - User Id
	 * @param kind - Type of User ( Fanatic, Liar, Naive or SelfCentered )
	 */
	public UserClass(String id, String kind) {
		this.id = id;
		this.kind = kind;
		friends = new TreeMap<String, User>();
		myPosts =  new ArrayList<Post>();
		myFeed =  new HashSet<Post>();
		commentsByTag = new HashMap<String, LinkedList<Comment>>();
		commentedPost = new HashSet<Post>();
		numComments = 0;
		numOfLies = 0;
	}
	
	public String getId() {
		return id;
	}

	public String getKind() {
		return kind;
	}
	
	public int getNumberOfLies() {
		return numOfLies;
	}

	public void newPost(Post post) {
		myPosts.add(post);
		if(!post.isHonest()) numOfLies++;
	}
	
	public boolean hasAccess(Post post) {
		return myFeed.contains(post) || myPosts.contains(post);
	}

	public void newComment(Comment comment) {
		Post post = comment.getPost();
		
		if(!commentedPost.contains(post)) {
			commentedPost.add(post);
		}
		
		addToMap(post ,comment);
		
		post.newComment(comment);
		
		numComments++;
		
		if(!post.isHonest() && comment.isPositive()) numOfLies++;
		else if(post.isHonest() && !comment.isPositive()) numOfLies++;;
	}
	
	public float getPercentageCommentedPosts() {
		return (float)commentedPost.size()/getNumCanCommentPosts();
	}
	
	public void addFeed(Post post) {
		myFeed.add(post);
	}
	
	public void addFriend(User user) {
		if(hasFriend(user)) throw new UsersAlreadyFriendsException();
		friends.put(user.getId(), user);
	}
	
	public int getNumCanCommentPosts() {
		return getNumberPosts()+myFeed.size();
	}
	
	public int getNumberFriends() {
		return friends.size();
	}
	
	public int getNumberPosts() {
		return myPosts.size();
	}
	
	public int getTotalNumberComments() {
		return numComments;
	}
	
	public Post getPost(int postId) {
		if(postId > myPosts.size() || postId < 1 ) throw new UserHasNoPostsException();
		return myPosts.get(postId-1);
	}
	
	public void sharePost(Post post) {
		for(User user: friends.values()) {
			user.addFeed(post);	
		}
	}
	
	public Iterator<User> getFriendIterator() {
		return friends.values().iterator();
	}
	
	public Iterator<Post> getMyPostsIterator() {
		return myPosts.iterator();
	}
	
	public Iterator<Comment> readPost(Post post) {
		if(post.getNumComments() == 0) throw new NoCommentsException();
		return post.readPost();
	}
	
	public Iterator<Comment> getListCommentByUser(String hashtag) {
		List<Comment> comment = commentsByTag.get(hashtag);
		if(comment==null) throw new NoCommentsException();
		return comment.iterator();
	}
	
	/**Adds the Comment to the designated linkedList inside the commentsByTag Map, 
	 * if there is no list with the Post's tag creates a new one and puts it into the map.
	 * @param post - Post
	 * @param comment - Comment
	 */
	private void addToMap(Post post, Comment comment) {
		Iterator<String> it = post.getHashTags();
		while(it.hasNext()) {
			String tag = it.next();
			LinkedList<Comment> list = commentsByTag.get(tag);
			if(list == null) {
				list = new LinkedList<Comment>();
				commentsByTag.put(tag,list);
			}
			list.add(comment);
		}
	}
	
	/**Verifies if the User is friends with the given User.
	 * @param user - Given User
	 * @return True if they are friends.
	 */
	private boolean hasFriend(User user) {
		return friends.get(user.getId()) !=null;
	}

}
