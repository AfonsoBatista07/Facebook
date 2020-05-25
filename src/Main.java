import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import system.exceptions.*;
import user.exceptions.*;
import post.*;
import system.*;
import user.User;

public class Main {

	/* Commands Constants */
	private static final String ADD_USER = "REGISTER";
	private static final String ADD_FRIEND = "ADDFRIEND";
	private static final String NEW_POST = "POST";
	private static final String LIST_POSTS = "USERPOSTS";
	private static final String NEW_COMMENT = "COMMENT";
	private static final String LIST_FRIENDS = "FRIENDS";
	private static final String LIST_USERS = "USERS";
	private static final String READ = "READPOST";
	private static final String LIST_COMMENTS = "COMMENTSBYUSER";
	private static final String LIST_TOPIC_FANATICS = "TOPICFANATICS";
	private static final String LIST_TOPIC_POSTS = "TOPICPOSTS";
	private static final String POPULAR_POSTS = "POPULARPOST";
	private static final String TOP_POSTER = "TOPPOSTER";
	private static final String RESPONSIVE = "RESPONSIVE";
	private static final String SHAMELESS = "SHAMELESS";
	private static final String EXIT = "EXIT";
	private static final String HELP = "HELP";
	
	/* Success Constants */
 	private static final String SUCCESS_EXIT = "Bye!";
 	private static final String SUCCESS_ADD_FRIEND = "%s is friend of %s.\n";
 	private static final String SUCCESS_ADD_USER = "%s registered.\n";
 	private static final String SUCCESS_NEW_POST = "%s sent a %s post to %d friends. Post id = %s.\n";
 	private static final String SUCCESS_NEW_COMMENT = "Comment added!";
 	private static final String SUCCESS_LIST_USERS = "%s [%s] %d %d %d\n";
 	private static final String SUCCESS_LIST_POSTS = "%d. [%s] %s [%s comments]\n";
 	private static final String SUCCESS_USER_POSTS = "%s posts:\n";
 	private static final String SUCCESS_LIST_COMMENTS = "[%s %s %d %s] %s\n";
 	private static final String SUCCESS_READ_POST = "[%s %s] %s\n";
 	private static final String SUCCESS_LIST_TOPIC_POSTS = "%s %d %d: %s\n";
 	private static final String SUCCESS_SHAMELESS = "%s %d.\n";
 	private static final String SUCCESS_TOP_POSTER = "%s %d %d.\n";
 	private static final String SUCCESS_HELP = "register - registers a new user\n" + 
 			"users - lists all users\n" + 
 			"addfriend - adds a new friend\n" + 
 			"friends - lists the user friends\n" + 
 			"post - posts a new message\n" + 
 			"userposts - lists all posts by a user\n" + 
 			"comment - user comments on a post\n" + 
 			"readpost - prints detailed info on a post\n" + 
 			"commentsbyuser - shows all the comments by a user on a given post\n" + 
 			"topicfanatics - shows a list of fanatic users on a topic\n" + 
 			"topicposts - shows a list of posts on a given topic\n" + 
 			"popularpost - shows the most commented post\n"+
 			"topposter - shows the user with more posts\n" + 
 			"responsive - shows the user with a higher percentage of commented posts\n" + 
 			"shameless - shows the top liars\n" + 
 			"help - shows the available commands\n" + 
 			"exit - terminates the execution of the program\n";
 	
 	/* Error Constants */
	private static final String UNKNNOWN_COMMAND = "Unknown command. Type help to see available commands.\n";
 	private static final String ERROR_INADEQUATE_COMMMENT_STANCE = "Inadequate stance!";
 	private static final String ERROR_INVALID_HASHTANGS = "Invalid hashtags list!";
 	private static final String ERROR_INVALID_COMMMENT_STANCE = "Invalid comment stance!";
 	private static final String ERROR_INVALID_FANATICISM = "Invalid fanaticism list!";
 	private static final String ERROR_INVALID_NUMBER_POSTS = "Invalid number of posts to present!\n";
 	private static final String ERROR_NO_KING_OF_LIARS = "Social distancing has reached fakebook. Post a lie and become the king of liars.";
 	private static final String ERROR_NO_KING_OF_RESPONSIVENESS = "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";
 	private static final String ERROR_NO_KING_POSTERS = "Social distancing has reached fakebook. Post something to become the king of posters.";
 	private static final String ERROR_NO_KING_POPULAR_POST = "Social distancing has reached fakebook. Please post something.";
 	private static final String ERROR_NO_POSTS = "%s has no posts!\n";
 	private static final String ERROR_NO_USERS = "There are no users!";
 	private static final String ERROR_NO_COMMENTS = "No comments!";
 	private static final String ERROR_NO_FRIENDS = "%s has no friends!\n";
 	private static final String ERROR_UNKNOWN_FANATICISM = "Oh please, who would be a fanatic of %s?\n";
 	private static final String ERROR_UNKNOWN_TOPIC = "Oh please, who would write about %s?\n";
 	private static final String ERROR_UNKNOWN_USER_KIND = "%s is an invalid user kind!\n";
 	private static final String ERROR_USER_ALREADY_EXISTS= "%s already exists!\n";
 	private static final String ERROR_USER_DOES_NOT_EXISTS = "%s does not exist!\n";
 	private static final String ERROR_USER_NO_POSTS =  "%s has no post %d!\n";  
 	private static final String ERROR_USER_NO_ACCESS_TO_POST = "%s has no access to post %s by %s!\n";
 	private static final String ERROR_USERS_FRIENDS_ALREADY = "%s must really admire %s!\n";
 	private static final String ERROR_USER_CAN_NOT_COMMENT = "%s cannot comment on this post!\n";
 	private static final String ERROR_SAME_USER = "%s cannot be the same as %s!\n";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		FakeSystem fsys = new FakeSystemClass();
		String cm;
		do{
			cm = readOption(in);
			exeOption(in, fsys, cm);
		} while(!cm.equals(EXIT));
		in.close();
		
	}
	
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}
	
	private static void exeOption(Scanner in, FakeSystem fsys, String option) {
		switch(option) {
			case ADD_USER:
				addUser(in, fsys);
				break;
			case ADD_FRIEND:
				addFriend(in, fsys);
				break;
			case NEW_POST:
				newPost(in, fsys);
				break;
			case NEW_COMMENT:
				newComment(in, fsys);
				break;
			case READ:
				readPost(in, fsys);
				break;
			case LIST_POSTS:
				listPosts(in, fsys);
				break;
			case LIST_FRIENDS:
				listFriends(in, fsys);
				break;
			case LIST_USERS:
				listUsers(in, fsys);
				break;
			case LIST_COMMENTS:
				listComments(in, fsys);
				break;
			case LIST_TOPIC_FANATICS:
				listTopicFanatics(in, fsys);
				break;
			case LIST_TOPIC_POSTS:
				listTopicPosts(in, fsys);
				break;
			case POPULAR_POSTS:
				popularPosts(fsys);
				break;
			case TOP_POSTER:
				topPoster(fsys);
				break;
			case RESPONSIVE:
				responsive(fsys);
				break;
			case SHAMELESS:
				shameless(fsys);
				break;
			case HELP:
				help();
				break;
			case EXIT:
				exit();
				break;
			default:
				wrongCommand(in);
		}
	}
	
	private static void wrongCommand(Scanner in) {
		in.nextLine();
		System.out.printf(UNKNNOWN_COMMAND);
	}
	
	private static void addUser(Scanner in, FakeSystem fsys) {
		String kind = in.next();
		String userId = in.nextLine().trim();
		
		int numberFanaticisms = 0;
		LinkedList<String> sequence = new LinkedList<String>() ;
		if(fsys.isFanatic(kind)) {
			numberFanaticisms = in.nextInt();
			for(int i=0; i<numberFanaticisms*2; i++) {
				sequence.add(in.next().trim());          
			}
		}
		
		try {
			fsys.addUser(kind, userId, numberFanaticisms, sequence);
			System.out.printf(SUCCESS_ADD_USER, userId);
			
		} catch (UnknownUserKindException e) {
			System.out.printf(ERROR_UNKNOWN_USER_KIND, kind);
		} catch (UserAlreadyExistsException e) {
			System.out.printf(ERROR_USER_ALREADY_EXISTS, e.getUserId());
		} catch (InvalidFanaticismListException e) {
			System.out.println(ERROR_INVALID_FANATICISM);
		}
	}

	private static void addFriend(Scanner in, FakeSystem fsys) {
		String firstUserId = in.nextLine().trim();
		String secondUserId = in.nextLine().trim();
		
		try {
			fsys.addFriend(firstUserId, secondUserId);
			System.out.printf(SUCCESS_ADD_FRIEND, firstUserId, secondUserId);
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (UserCanNotBeTheSameException e) {
			System.out.printf(ERROR_SAME_USER, firstUserId, secondUserId);
		} catch (UsersAlreadyFriendsException e) {
			System.out.printf(ERROR_USERS_FRIENDS_ALREADY, firstUserId, secondUserId);
		}
	}
	
	private static void newPost(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		int hashtagsNumber = in.nextInt();
		
		LinkedList<String> hashtags = new LinkedList<String>();; 
		for(int i = 0; i < hashtagsNumber; i++) {
			hashtags.add(in.next());
		}
		String truthfulness = in.next();
		String message = in.nextLine().trim();
		
		try {
			fsys.newPost(userId, hashtagsNumber, hashtags, truthfulness, message);
			System.out.printf(SUCCESS_NEW_POST, userId, truthfulness, fsys.getNumberFriends(userId), fsys.getNumPosts(userId));
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (InvalidHashtagsListException e) {
			System.out.println(ERROR_INVALID_HASHTANGS);
		} catch (InadequateStanceException e) {
			System.out.println(ERROR_INADEQUATE_COMMMENT_STANCE);
		}
	}
	
	private static void newComment(Scanner in, FakeSystem fsys) {
		
		String idUserComment = in.nextLine().trim();
		String idUserAuthor = in.nextLine().trim();
		int idPost = in.nextInt();
		String stance = in.next();
		String comment = in.nextLine().trim();
		
		try {
			fsys.addComment(idUserComment, idUserAuthor, idPost, stance, comment);
			System.out.println(SUCCESS_NEW_COMMENT);
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());   
		} catch (UserHasNoPostsException e) {
			System.out.printf(ERROR_USER_NO_POSTS, idUserAuthor, idPost);
		} catch (UserNoAccessToPostException e) {
			System.out.printf(ERROR_USER_NO_ACCESS_TO_POST, idUserComment, idPost, idUserAuthor);
		} catch (UserCanNotCommentPostException e) {
			System.out.printf(ERROR_USER_CAN_NOT_COMMENT, idUserComment);
		} catch (InvalidCommentStanceException e) {
			System.out.println(ERROR_INVALID_COMMMENT_STANCE);
		}
	}
	
	private static void listUsers(Scanner in, FakeSystem fsys) {
		try {
			Iterator<User> it = fsys.listUsers();
			while(it.hasNext()) {
				User user = it.next();
				System.out.printf(SUCCESS_LIST_USERS, user.getId(), user.getKind(),
						user.getNumberFriends(), user.getNumberPosts(), user.getTotalNumberComments());
			}
		} catch (NoUsersException e) {
			System.out.println(ERROR_NO_USERS);
		}
	}
	
	private static void listFriends(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		
		try {
			Iterator<User> it = fsys.listFriends(userId);
			while(it.hasNext()) {
				System.out.print(it.next().getId());
				if(it.hasNext())
					System.out.print(", ");
			}
			System.out.print(".\n");
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (NoFriendsException e) {
			System.out.printf(ERROR_NO_FRIENDS, userId);
		}
	}
	
    private static void listPosts(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		try {
			Iterator<Post> it = fsys.listUserPosts(userId); 
			System.out.printf(SUCCESS_USER_POSTS, userId);
			while(it.hasNext()) {
				Post post = it.next();
				System.out.printf(SUCCESS_LIST_POSTS, post.getIdPost(),
						post.getType(), post.getMessage(), post.getNumComments());
			}
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (NoPostsException e) {
			System.out.printf(ERROR_NO_POSTS, userId);
		}
	}
    
    private static void readPost(Scanner in, FakeSystem fsys) {
    	String userId = in.nextLine().trim();
    	int postId = in.nextInt();
    	try {
    		Post post = fsys.getPost(userId, postId);
        	System.out.printf(SUCCESS_READ_POST, post.getAuthorId(), post.getType(), post.getMessage());   
        	Iterator<Comment> it = fsys.readPost(post);
        	while(it.hasNext()) {
        		Comment cmt = it.next();
        		System.out.printf(SUCCESS_READ_POST, cmt.getUserId(), cmt.getStance(), cmt.getComment());
        	}
    	} catch (UserDoesNotExistException e) {
        	System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
        } catch (UserHasNoPostsException e) {
        	System.out.printf(ERROR_USER_NO_POSTS, userId, postId);
        } catch (NoCommentsException e) {
			System.out.println(ERROR_NO_COMMENTS);
        }
	}
    
    private static void listComments(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		String hashtag = in.next();
		
		try {
			Iterator<Comment> it = fsys.listCommentByUser(userId, hashtag);
			while(it.hasNext()) {
				Comment cmt = it.next();
				Post post = cmt.getPost();
				System.out.printf(SUCCESS_LIST_COMMENTS, post.getAuthorId(), post.getType(), post.getIdPost(), cmt.getStance(), cmt.getComment());
			}
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (NoCommentsException e) {
			System.out.println(ERROR_NO_COMMENTS);
        }
	}
    
    private static void listTopicFanatics(Scanner in, FakeSystem fsys) {
		String hashtag = in.next();
		
		try {
			Iterator<String> it = fsys.listFanaticsByTopic(hashtag);
			while(it.hasNext()) {
				System.out.printf(it.next());
				if(it.hasNext()) System.out.printf(", ");
				else System.out.printf(".");						//Fazer Constantes
			}
			System.out.println();
		} catch(UnknownFanaticismException e) {
			System.out.printf(ERROR_UNKNOWN_FANATICISM, hashtag);
		}
	}

	private static void listTopicPosts(Scanner in, FakeSystem fsys) {
		String hashtag = in.next();
		int numberOfPosts = in.nextInt();
		
		try {
			Iterator<Post> it = fsys.listTopicPosts(numberOfPosts, hashtag);
			while(it.hasNext() && numberOfPosts > 0) {										//É bad code usar a variavel?
				Post post = it.next();
				System.out.printf(SUCCESS_LIST_TOPIC_POSTS ,post.getAuthorId(), post.getIdPost(),post.getNumComments(), post.getMessage());
				numberOfPosts--;
			}
		} catch ( InvalidNumberOfPostsException e) {
			System.out.printf(ERROR_INVALID_NUMBER_POSTS);
		} catch ( UnKnownTopicException e ) {
			System.out.printf(ERROR_UNKNOWN_TOPIC, hashtag);
		}
	}

	private static void shameless(FakeSystem fsys) {
		try {
			User user = fsys.getShameless();
			System.out.printf(SUCCESS_SHAMELESS, user.getId(), user.getNumberOfLies());
		} catch(NoKingOfLiarsException e) {
			System.out.println(ERROR_NO_KING_OF_LIARS);
		}
	}

	private static void responsive(FakeSystem fsys) {
		try {
			User user = fsys.getResponsive();
			System.out.printf(SUCCESS_TOP_POSTER, user.getId(), user.getTotalNumberComments(), user.getNumCanCommentPosts());
		} catch (NoKingOfResponsivenessException e) {
			System.out.println(ERROR_NO_KING_OF_RESPONSIVENESS);
		}
	}

	private static void topPoster(FakeSystem fsys) {
		try {
			User user = fsys.getTopPoster();
			System.out.printf(SUCCESS_TOP_POSTER, user.getId(), user.getNumberPosts(), user.getTotalNumberComments());
		} catch(NoKingPostersException e) {
			System.out.println(ERROR_NO_KING_POSTERS);
		}
	}

	private static void popularPosts(FakeSystem fsys) {
		try {
			Post post = fsys.getPopularPost();
			System.out.printf(SUCCESS_LIST_TOPIC_POSTS, post.getAuthorId(), post.getIdPost(), post.getNumComments(), post.getMessage());
		} catch(NoKingPopularPostException e) {
			System.out.println(ERROR_NO_KING_POPULAR_POST);
		}
	}

	private static void exit() {
		System.out.println(SUCCESS_EXIT);
	}
	
	private static void help() {
		System.out.print(SUCCESS_HELP);
	}
	
}

