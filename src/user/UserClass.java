package user;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.NoCommentsException;
import exceptions.UsersAlreadyFriendsException;
import post.Comment;
import post.Post;

public abstract class UserClass implements User {
	
	private String id, kind;
	private SortedMap<String, User> friends;
	protected ArrayList<Post> myPosts, myFeed;
	private Map<String,LinkedList<Comment>> commentsByTag;
	private int totalNumComments, numComments, numOfLies;
	
	public UserClass(String id, String kind) {
		this.id = id;
		this.kind = kind;
		friends = new TreeMap<String, User>();
		myPosts =  new ArrayList<Post>();
		myFeed =  new ArrayList<Post>();
		commentsByTag = new HashMap<String, LinkedList<Comment>>();
		totalNumComments = 0;
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

	public void newComment(Comment comment) {
		Post post = comment.getPost();
		
		if(!post.hasComment(getId())) numComments++;
		
		Iterator<String> it = post.getHashTags();
		while(it.hasNext()) {
			addToList(it, comment);
		}
		if(!post.hasComment(getId())) numComments++;
		post.newComment(comment);
		totalNumComments++;
		
		if(!post.isHonest() && comment.isPositive()) numOfLies++;
		else if(post.isHonest() && !comment.isPositive()) numOfLies++;;
	}
	
	private void addToList(Iterator<String> it, Comment comment) {
		String tag = it.next();
		LinkedList<Comment> list = commentsByTag.get(tag);
		if(list == null) {
			list = new LinkedList<Comment>();
			commentsByTag.put(tag,list);
		}
		list.add(comment);
	}
	
	public float getPercentageCommentedPosts() {
		return (float)numComments/getNumCanCommentPosts();
	}
	
	public void addFeed(Post post) {
		myFeed.add(post);
	}
	
	public void addFriend(User user) {
		if(hasFriend(user)) throw new UsersAlreadyFriendsException();
		friends.put(user.getId(), user);
	}
	
	public int getNumCanCommentPosts() {
		return getNumberPosts()+getNumFriendPosts();
	}
	
	public int getNumberFriends() {
		return friends.size();
	}
	
	public int getNumFriendPosts() {
		return myFeed.size();
	}
	
	public int getNumberPosts() {
		return myPosts.size();
	}
	
	public int getTotalNumberComments() {
		return totalNumComments;
	}
	
	public boolean hasFriend(User user) {
		return getFriend(user)!=null;
	}
	
	public boolean hasPost(int postId) {
		return getPost(postId)!=null;
	}
	
	public Post getPost(int postId) {
		if(postId-1 >= myPosts.size() || postId<=0) return null;
		return myPosts.get(postId-1);
	}
	
	public User getFriend(User user) {
		return friends.get(user.getId());       
	}
	
	public void sharePost(Post post) {
		for(User user: friends.values())
			user.addFeed(post);	
	}
	
	public Iterator<User> getFriendIterator() {
		return friends.values().iterator();
	}
	
	public Iterator<Post> getMyPostsIterator() {
		return myPosts.iterator();
	}
	
	public Iterator<Post> getMyFeedIterator() {
		return myFeed.iterator();
	}
	
	public Iterator<Comment> readPost(Post post) {
		return post.readPost();
	}
	
	public Iterator<Comment> getListCommentByUser(String hashtag) {
		LinkedList<Comment> comment = commentsByTag.get(hashtag);
		if(comment==null) throw new NoCommentsException();
		return commentsByTag.get(hashtag).iterator();
	}

}
