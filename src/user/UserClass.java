package user;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.UsersAlreadyFriendsException;
import post.Comment;
import post.Post;

public abstract class UserClass implements User {
	
	private String id, kind;
	private LinkedList<User> friends;
	protected ArrayList<Post> myPosts, myFeed;
	private Map<String,LinkedList<Comment>> commentsByTag;
	private int numComments;
	
	public UserClass(String id, String kind) {
		this.id = id;
		this.kind = kind;
		friends = new LinkedList<User>();
		myPosts =  new ArrayList<Post>();
		myFeed =  new ArrayList<Post>();
		commentsByTag = new HashMap<String, LinkedList<Comment>>();
		numComments = 0;
	}
	
	public String getId() {
		return id;
	}

	public String getKind() {
		return kind;
	}

	public void newPost(Post post) {
		myPosts.add(post);
	}

	public void newComment(int postId, Comment comment) {
		Post post = getPost(postId);
		Iterator<String> it = post.getHashTags();
		while(it.hasNext()) {
			String tag = it.next();
			LinkedList<Comment> list = commentsByTag.get(tag);
			if (list == null) {
				list = new LinkedList<Comment>();
				commentsByTag.put(tag,list);
			}
			list.add(comment);
		}
		post.newComment(comment);
		numComments++;
	}
	
	public void addFeed(Post post) {
		myFeed.add(post);
	}
	
	public void addFriend(User user) {
		if(hasFriend(user)) throw new UsersAlreadyFriendsException();
		friends.addLast(user);
	}
	
	public int getNumberFriends() {
		return friends.size();
	}
	
	public int getNumberPosts() {
		return myPosts.size();
	}
	public int getNumberComments() {
		return numComments;
	}
	
	public boolean hasFriend(User user) {
		return getFriend(user)!=null;
	}
	
	public boolean hasPost(int postId) {
		return getPost(postId)!=null;
	}
	
	public Post getPost(int postId) {
		return myPosts.get(postId-1);
	}
	
	public User getFriend(User user) {
		for( User acc: friends)
			if (acc.getId().equals(user.getId()))
				return acc;
		return null;
	}
	
	public void sharePost(Post post) {
		for(User user: friends)
			user.addFeed(post);	
	}
	
	public Iterator<User> getFriendIterator() {
		return friends.iterator();
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
	
	public Iterator<Comment> getListCommentByUser(String hashtag) {               // ??????????????????????????????????
		return commentsByTag.get(hashtag).iterator();
	}

}
