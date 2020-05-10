package user;

import java.util.Iterator;

import post.Comment;
import post.Post;

public interface User {

	public static final String FANATIC = "fanatic";
	public static final String LIAR = "liar";                 // PUBLIC !?
	public static final String NAIVE = "naive";                       
	public static final String	SELF_CENTERED = "selfcentered";
	
	String getId();
	String getKind();
	void newPost(Post post);
	void newComment(Comment comment);
	void addFeed(Post post);
	void addFriend(User user);
	int getNumberFriends();
	int getNumberPosts();
	int getNumberComments();
	boolean hasFriend(User user);
	Iterator<User> getFriendIterator();
	Iterator<Post> getMyPostsIterator();
	Iterator<Post> getMyFeedIterator();
	Iterator<Comment> getCommentsIterator();
	
}
