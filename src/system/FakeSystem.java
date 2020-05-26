package system;

import java.util.Iterator;
import java.util.LinkedList;

import post.Post;
import system.exceptions.*;
import user.exceptions.*;
import post.Comment;
import user.User;


/**
 * Interface of the FakeSystemClass processes all the information from the other classes
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public interface FakeSystem {
	
	/**
	 * @param userId - Id of the User.
	 * @return true if the user exits.
	 */
	boolean userExists(String userId);
	
	/**
	 * @param kind - User kind.
	 * @return true if the user kind is Fanatic.
	 */
	boolean isFanatic(String kind);
	
	/**
	 * @param kind - User kind.
	 * @param userId - Id of the User
	 * @param numberFanaticisms - number of fanaticisms.
	 * @param sequence - sequence of fanaticisms.
	 * @throws UnknownUserKindException If the user kind is unknown.
	 * @throws UserAlreadyExistsException If there is already a user with the same id.
	 * @throws InvalidFanaticismListException If the user is a fanatic but there are repeated fanaticisms in the user's list.
	 */
	void addUser(String kind, String userId, int numberFanaticisms, LinkedList<String> sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	/**
	 * @param idUserComment - Id of the
	 * @param idUserAuthor
	 * @param idPost
	 * @param stance
	 * @param comment
	 * @throws UserDoesNotExistException
	 * @throws UserHasNoPostsException
	 * @throws UserCanNotCommentPostException
	 * @throws UserNoAccessToPostException
	 * @throws InvalidCommentStanceException
	 */
	void addComment(String idUserComment, String idUserAuthor, int idPost, String stance, String comment) throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotCommentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	/**
	 * @param firstUserId
	 * @param secondUserId
	 * @throws UserDoesNotExistException
	 * @throws UsersAlreadyFriendsException
	 * @throws UserCanNotBeTheSameException
	 */
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistException, UsersAlreadyFriendsException, UserCanNotBeTheSameException;
	
	/**
	 * @param userId
	 * @param hashtagsNumber
	 * @param hashtags
	 * @param truthfulness
	 * @param message
	 * @throws UserDoesNotExistException
	 * @throws InvalidHashtagsListException
	 * @throws InadequateStanceException
	 */
	void newPost(String userId, int hashtagsNumber, LinkedList<String> hashtags, String truthfulness, String message) throws UserDoesNotExistException, InvalidHashtagsListException, InadequateStanceException;
	
	/**
	 * @param userId
	 * @return
	 */
	int getNumFriends(String userId);
	
	/**
	 * @param userId
	 * @return
	 */
	int getNumPosts(String userId);
	
	/**
	 * @return
	 * @throws NoKingPopularPostException
	 */
	Post getPopularPost() throws NoKingPopularPostException;
	
	/**
	 * @return
	 * @throws NoKingPostersException
	 */
	User getTopPoster() throws NoKingPostersException;
	
	/**
	 * @return
	 * @throws NoKingOfResponsivenessException
	 */
	User getResponsive() throws NoKingOfResponsivenessException;
	
	/**
	 * @return
	 * @throws NoKingOfLiarsException
	 */
	User getShameless() throws NoKingOfLiarsException;
	
	/**
	 * @param userId
	 * @param postId
	 * @return
	 */
	Post getPost(String userId, int postId);
	
	/**
	 * @param post
	 * @return
	 * @throws UserDoesNotExistException
	 * @throws UserHasNoPostsException
	 * @throws NoCommentsException
	 */
	Iterator<Comment> readPost(Post post) throws UserDoesNotExistException, UserHasNoPostsException, NoCommentsException;
	
	/**
	 * @return
	 * @throws NoUsersException
	 */
	Iterator<User> listUsers() throws NoUsersException;
	
	/**
	 * @param userId
	 * @return
	 * @throws UserDoesNotExistException
	 * @throws NoFriendsException
	 */
	Iterator<User> listFriends(String userId) throws UserDoesNotExistException, NoFriendsException;
	
	/**
	 * @param userId
	 * @return
	 * @throws UserDoesNotExistException
	 * @throws NoPostsException
	 */
	Iterator<Post> listUserPosts(String userId) throws UserDoesNotExistException, NoPostsException;
	
	/**
	 * @param userId
	 * @param hashtag
	 * @return
	 * @throws UserDoesNotExistException
	 * @throws NoCommentsException
	 */
	Iterator<Comment> listCommentByUser(String userId, String hashtag) throws UserDoesNotExistException, NoCommentsException;
	
	/**
	 * @param hashtag
	 * @return
	 * @throws UnknownFanaticismException
	 */
	Iterator<String> listFanaticsByTopic(String hashtag) throws UnknownFanaticismException; 
	
	/**
	 * @param numberOfPosts
	 * @param hashtag
	 * @return
	 * @throws UnKnownTopicException
	 * @throws InvalidNumberOfPostsException
	 */
	Iterator<Post> listTopicPosts(int numberOfPosts, String hashtag) throws UnKnownTopicException, InvalidNumberOfPostsException; 
}
