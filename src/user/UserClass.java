package user;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import post.Comment;
import post.Post;
import system.exceptions.NoCommentsException;
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
	private int numComments, numPostsCommented, numOfLies;
	
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
		numComments = 0;
		numPostsCommented = 0;
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
		
		if(!post.hasComment(getId())) numPostsCommented++;
		
		addToMap(post ,comment);
		
		post.newComment(comment);
		
		numComments++;
		
		if(!post.isHonest() && comment.isPositive()) numOfLies++;
		else if(post.isHonest() && !comment.isPositive()) numOfLies++;;
	}
	
	public float getPercentageCommentedPosts() {
		return (float)numPostsCommented/getNumCanCommentPosts();
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
		if(postId > myPosts.size() || postId < 1 ) throw new UserHasNoPostsException();  // this was changed
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
		return post.readPost();
	}
	
	public Iterator<Comment> getListCommentByUser(String hashtag) {
		List<Comment> comment = commentsByTag.get(hashtag);
		if(comment==null) throw new NoCommentsException();
		return commentsByTag.get(hashtag).iterator();
	}
	
	/**Adds the Comment to the designated linkedList inside the commentsByTag Map, 
	 * if there is no list with the posts tag creates a new one and puts it into the map.
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
	 * @return True - is they are friends, False - if not.
	 */
	private boolean hasFriend(User user) {
		return friends.get(user.getId()) !=null;
	}

}
