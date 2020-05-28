package system;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import post.Post;
import system.exceptions.*;
import user.exceptions.*;
import post.Comment;
import user.User;


/**
 * Interface of the FakeSystemClass processes all the information from the other classes.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface FakeSystem {
	
	/**
	 * Registers a User into the System.
	 * @param kind - User kind (Fanatic, Naive, Self Centered, Liar).
	 * @param userId - Id of the User
	 * @param numberFanaticisms - number of fanaticisms.
	 * @param sequence - sequence of fanaticisms.
	 * @throws UnknownUserKindException If the user kind is unknown.
	 * @throws UserAlreadyExistsException If there is already a user with the same id.
	 * @throws InvalidFanaticismListException If the user is a fanatic but there are repeated fanaticisms in the users list.
	 */
	void addUser(String kind, String userId, int numberFanaticisms, List<String> sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	/**
	 * Registers a Comment into the System.
	 * Updates if needed, the most popular Post, the most responsive User and the most shameless User.
	 * @param idUserComment - Id of the user commenting.
	 * @param idUserAuthor - Id of the user author of the post.
	 * @param postId - Id of the Post.
	 * @param stance - Fake or Honest.
	 * @param comment - Comment.
	 * @throws UserDoesNotExistException If the user id of either the author of the comment, or the author of the post is unknown.
	 * @throws UserHasNoPostsException If the post id does not exist for that author.
	 * @throws UserCanNotCommentPostException If the user is friends with the author of the post, but is not allowed to comment on that post.
	 * @throws UserNoAccessToPostException If the user cannot comment that post, because he has not received it from the author
	 * @throws InvalidCommentStanceException If the comment stance is invalid for that user id and that post.
	 */
	void addComment(String idUserComment, String idUserAuthor, int postId, String stance, String comment) throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotCommentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	/**
	 * Makes the Users given friends.
	 * @param firstUserId - Id of the first user.
	 * @param secondUserId - Id of the second user.
	 * @throws UserDoesNotExistException If some of the users does not exist.
	 * @throws UsersAlreadyFriendsException If the first user is already a friend of the second user.
	 * @throws UserCanNotBeTheSameException If the first and second users are the same.
	 */
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistException, UsersAlreadyFriendsException, UserCanNotBeTheSameException;
	
	/**
	 * Registers a Post into the System.
	 * Updates if needed, the most popular Post, the most responsive User and the most shameless User.
	 * @param userId - Id of the first user.
	 * @param hashtagsNumber - Number of hashTags.
	 * @param hashtags - HashTags.
	 * @param truthfulness - Positive or negative.
	 * @param message - Message.
	 * @throws UserDoesNotExistException If the user id is unknown.
	 * @throws InvalidHashtagsListException If the number of hashTags is not greater or equal to 0.
	 * @throws InadequateStanceException If the post stance contradicts the user’s stance.
	 */
	void newPost(String userId, int hashtagsNumber, List<String> hashtags, String truthfulness, String message) throws UserDoesNotExistException, InvalidHashtagsListException, InadequateStanceException;
	
	/**
	 * @param kind - User kind (Fanatic, Naive, Self Centered, Liar).
	 * @return true if the user kind given is Fanatic.
	 */
	boolean isFanatic(String kind);
	
	/**
	 * @param userId - Id of the user.
	 * @return Number of friends, the user have.
	 */
	int getNumFriends(String userId);
	
	/**
	 * @param userId - Id of the user.
	 * @return Number of posts, the user have.
	 */
	int getNumPosts(String userId);
	
	/**
	 * @return The most commented post.
	 * @throws NoKingPopularPostException If there are no posts, or no comments, on fakebook.
	 */
	Post getPopularPost() throws NoKingPopularPostException;
	
	/**
	 * @return The id of the top poster.
	 * @throws NoKingPostersException If there are no posts on fakebook.
	 */
	User getTopPoster() throws NoKingPostersException;
	
	/**
	 * @return The id of the user with the higher percentage of commented posts
	 * @throws NoKingOfResponsivenessException If there are no posts on fakebook.
	 */
	User getResponsive() throws NoKingOfResponsivenessException;
	
	/**
	 * @return The id of the top liar.
	 * @throws NoKingOfLiarsException If nobody lied yet.
	 */
	User getShameless() throws NoKingOfLiarsException;
	
	/**
	 * @param userId - Id of the user.
	 * @param postId - Id of the post.
	 * @return Post.
	 */
	Post getPost(String userId, int postId);
	
	/**
	 * @param post - The post you want to read. 
	 * @return Iterator of all the post comments. 
	 * @throws UserDoesNotExistException If the user id is unknown.
	 * @throws UserHasNoPostsException If the user has no message with the given id.
	 * @throws NoCommentsException If there are no comments yet.
	 */
	Iterator<Comment> readPost(Post post) throws UserDoesNotExistException, UserHasNoPostsException, NoCommentsException;
	
	/**
	 * @return Iterator of all the system users.
	 * @throws NoUsersException If there are no registered users.
	 */
	Iterator<User> listUsers() throws NoUsersException;
	
	/**
	 * @param userId - Id of the user.
	 * @return Iterator of all the users friends.
	 * @throws UserDoesNotExistException If the user id is unknown.
	 * @throws NoFriendsException If the user has no friends.
	 */
	Iterator<User> listFriends(String userId) throws UserDoesNotExistException, NoFriendsException;
	
	/**
	 * @param userId - Id of the user.
	 * @return Iterator of all users posts.
	 * @throws UserDoesNotExistException If the user id is unknown.
	 * @throws NoPostsException If the user exists, but has no posts.
	 */
	Iterator<Post> listUserPosts(String userId) throws UserDoesNotExistException, NoPostsException;
	
	/**
	 * @param userId - Id of the user.
	 * @param hashtag - HashTag you want to search.
	 * @return Iterator of all users comments on a given hashTag.
	 * @throws UserDoesNotExistException If the user id is unknown.
	 * @throws NoCommentsException If the user has not made any comments.
	 */
	Iterator<Comment> listCommentByUser(String userId, String hashtag) throws UserDoesNotExistException, NoCommentsException;
	
	/**
	 * @param hashtag - hashTag you want to search.
	 * @return Iterator of all Fanatics on a given hashTag.
	 * @throws UnknownFanaticismException  If the kind of fanaticism is unknown.
	 */
	Iterator<String> listFanaticsByTopic(String hashtag) throws UnknownFanaticismException; 
	
	/**
	 * @param numberOfPosts - The number of posts you want to print.
	 * @param hashtag - HashTag you want to search.
	 * @return Iterator of all the posts on a given hashTag.
	 * @throws UnKnownTopicException If the topic id a is unknown.
	 * @throws InvalidNumberOfPostsException If the maximum number of posts to list is not greater or equal to 1.
	 */
	Iterator<Post> listTopicPosts(int numberOfPosts, String hashtag) throws UnKnownTopicException, InvalidNumberOfPostsException; 
}
