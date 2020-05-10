package user;
import java.util.Iterator;
import java.util.LinkedList;

import exceptions.UsersAlreadyFriendsException;
import post.Comment;
import post.Post;

public abstract class UserClass implements User {
	
	private String id, type;
	private LinkedList<User> friends;
	protected LinkedList<Post> myPosts, myFeed;
	private LinkedList<Comment> comments;
	
	public UserClass(String id, String type) {
		this.id = id;
		this.type = type;
		friends = new LinkedList<User>();
		myPosts =  new LinkedList<Post>();
		myFeed =  new LinkedList<Post>();
		comments = new LinkedList<Comment>();
	}
	
	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void newPost(Post post) {
		myPosts.addLast(post);
	}

	public void newComment(Comment comment) {
		comments.addLast(comment);
	}
	
	public void addFeed(Post post) {
		myFeed.addLast(post);
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
		return comments.size();
	}
	
	public boolean hasFriend(User user) {
		return getFriend(user)!=null;
	}
	
	private User getFriend(User user) {
		for( User acc: friends)
			if (acc.getId().equals(user.getId()))
				return acc;
		return null;
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
	
	public Iterator<Comment> getCommentsIterator() {
		return comments.iterator();
	}

}
