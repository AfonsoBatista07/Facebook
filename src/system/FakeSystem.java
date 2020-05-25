package system;

import java.util.Iterator;
import java.util.LinkedList;

import post.Post;
import system.exceptions.*;
import user.exceptions.*;
import post.Comment;
import user.User;

public interface FakeSystem {
	
	boolean userExists(String userId);
	
	boolean isFanatic(String kind);
	
	void addUser(String type, String userId, int numberFanaticisms, LinkedList<String> sequence) throws UnknownUserKindException, UserAlreadyExistsException, InvalidFanaticismListException;
	
	void addComment(String idUserComment, String idUserAuthor, int idPost, String stance, String comment) throws UserDoesNotExistException, UserHasNoPostsException, UserCanNotCommentPostException, UserNoAccessToPostException, InvalidCommentStanceException;
	
	void addFriend(String firstUserId, String secondUserId) throws UserDoesNotExistException, UsersAlreadyFriendsException;
	
	void newPost(String userId, int hashtagsNumber, LinkedList<String> hashtags, String truthfulness, String message);
	
	int getNumberFriends(String userId);
	
	int getNumPosts(String userId);
	
	Post getPopularPost() throws NoKingPopularPostException;
	
	User getTopPoster() throws NoKingPostersException;
	
	User getResponsive() throws NoKingOfResponsivenessException;
	
	User getShameless() throws NoKingOfLiarsException;
	
	Post getPost(String userId, int postId);
	
	Iterator<Comment> readPost(Post post) throws UserDoesNotExistException, UserHasNoPostsException, NoCommentsException;
	
	Iterator<User> listUsers() throws NoUsersException;
	
	Iterator<User> listFriends(String userId) throws UserDoesNotExistException, NoFriendsException;
	
	Iterator<Post> listUserPosts(String userId) throws UserDoesNotExistException, NoPostsException;
	
	Iterator<Comment> listCommentByUser(String userId, String hashtag) throws UserDoesNotExistException, NoCommentsException;
	
	Iterator<String> listFanaticsByTopic(String hashtag) throws UnknownFanaticismException; 
	
	Iterator<Post> listTopicPosts(int numberOfPosts, String hashtag) throws UnKnownTopicException, InvalidNumberOfPostsException; 
}
