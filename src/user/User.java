package user;

import java.util.Iterator;

import post.Comment;
import post.Post;
import user.exceptions.*;

/**
 * Interface of the UserClass.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface User {

	public static final String FANATIC = "fanatic";
	public static final String LIAR = "liar";                 
	public static final String NAIVE = "naive";                       
	public static final String SELF_CENTERED = "selfcentered";
	
	/**
	 * @return User Id.
	 */
	String getId();
	
	/**
	 * @return User's type of User ( Fanatic, Liar, Naive, SelfCentered ).
	 */
	String getKind();
	
	/**
	 * Adds Post to the myPosts List
	 * If the Post is fake increments the number of lies.
	 * @param post - Post
	 */
	void newPost(Post post);
	
	/**
	 * Adds Comment to the commentsByTag Map.
	 * Increments numPostsCommented (number of posts with at least one comment) if the User hasn't commented the Post yet.
	 * Increments numComment (total number of comments by the User).
	 * Increments numOfLies if the comment contradicts the Post's stance.
 	 * @param comment - Comment
	 */
	void newComment(Comment comment);
	
	/**
	 * Adds Post to the Users myFeed Set.
	 * @param post - Post
	 */
	void addFeed(Post post);
	
	/**
	 * Adds the Post to the feed of all the User's friends.
	 * @param post - Post
	 */
	void sharePost(Post post);
	
	/**
	 * Adds the friend to the User's SortedMap of friends.
	 * @param user - User (friend)
	 * @throws UsersAlreadyFriendsException if the Users are already friends.
	 */
	void addFriend(User user) throws UsersAlreadyFriendsException;
	
	/**
	 * Verifies if the User has access to the given Post.
	 * @param post - Post
	 * @return True if has access. False if not.
	 */
	boolean hasAccess(Post post);
	
	/**
	 * @return User's number of Lies.
	 */
	int getNumberOfLies();
	
	/**
	 * @return User's number of friends.
	 */
	int getNumberFriends();
	
	/**
	 * @return Number of Posts made by the User.
	 */
	int getNumberPosts();
	
	/**
	 * @return Number of Comments made by the User.
	 */
	int getTotalNumberComments();
	
	/**
	 * @return Number of Posts the User can Comment.
	 */
	int getNumCanCommentPosts();
	
	/**
	 * @return Percentage of Posts with at least one Comment made by the User.
	 */
	float getPercentageCommentedPosts();
	
	/**
	 * @param postId - Id of the Post
	 * @return The Post with the given id.
	 * @throws UserHasNoPostsException if there is no post with the given id.
	 */
	Post getPost(int postId);
	
	/**
	 * @return Iterator of the User's friends.
	 */
	Iterator<User> getFriendIterator();
	
	/**
	 * @return Iterator of the User's Posts.
	 */
	Iterator<Post> getMyPostsIterator();
	
	/**
	 * @param post - Post
	 * @return Iterator of the given Post's Comments.
	 * @throws NoCommentsException if the Post has no Comments.
	 */
	Iterator<Comment> readPost(Post post) throws NoCommentsException;
	
	/**
	 * @param hashtag - HashTag
	 * @return Iterator of the User's Comments with the given HashTag.
	 * @throws NoCommentsException if there is no Comments with the given HashTag.
	 */
	Iterator<Comment> getListCommentByUser(String hashtag) throws NoCommentsException;
	
}
