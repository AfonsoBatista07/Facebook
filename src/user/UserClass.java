package user;
import java.util.LinkedList;

import post.Post;

public abstract class UserClass implements User{
	private String id, type;
	private LinkedList<User> friends;
	private LinkedList<Post> myPosts, myFeed;
	
	public UserClass(String id, String type) {
		this.id = id;
		this.type = type;
		friends = new LinkedList<User>();
		myPosts =  new LinkedList<Post>();
		myFeed =  new LinkedList<Post>();
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	public abstract void newPost(String[] hashtags, String type, String message);

	public abstract void newComment(String idComment, String idPost, String stance, String comment);
	
	public void addFriend(User user) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean hasFriend(User user) {
		return false;
	}

}
