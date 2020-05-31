import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import system.exceptions.*;
import user.exceptions.*;
import post.*;
import system.*;
import user.User;

/**
 * Main Program.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class Main {

	/* Success Constants */
	private static final String DOT = ".";
	private static final String COMA = ", ";
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
		Command cm;
		do{
			cm = getCommand(in);
			exeOption(in, fsys, cm);
		} while(!cm.equals(Command.EXIT));
		in.close();
		
	}
	
	private enum Command {
		REGISTER("registers a new user"), USERS("lists all users"),
		ADDFRIEND("adds a new friend"), FRIENDS("lists the user friends"),
		POST("posts a new message"), USERPOSTS("lists all posts by a user"),
		COMMENT("user comments on a post"), READPOST("prints detailed info on a post"),
		COMMENTSBYUSER("shows all the comments by a user on a given post"),
		TOPICFANATICS("shows a list of fanatic users on a topic"),
		TOPICPOSTS("shows a list of posts on a given topic"),
		POPULARPOST("shows the most commented post"), TOPPOSTER("shows the user with more posts"),
		RESPONSIVE("shows the user with a higher percentage of commented posts"),
		SHAMELESS("shows the top liars"), HELP("shows the available commands"),
		EXIT("terminates the execution of the program"), UNKNOWN("");
		
		private String description;

		Command (String description) { this.description = description; }
		/**
		 * @return The description of ther command.
		 */
		public String getDescription() { return this.description; }
		
	};
	
	/**
	 * @param in - Scanner
	 * @return The user input command.
	 */
	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		} catch(IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}
	
	/**
	 * Execute one available option.
	 * @param in - Scanner.
	 * @param sec - SecuritySystem
	 * @param option - the user input.
	 */
	private static void exeOption(Scanner in, FakeSystem fsys, Command cm) {
		switch(cm) {
			case REGISTER:
				addUser(in, fsys);
				break;
			case ADDFRIEND:
				addFriend(in, fsys);
				break;
			case POST:
				newPost(in, fsys);
				break;
			case COMMENT:
				newComment(in, fsys);
				break;
			case READPOST:
				readPost(in, fsys);
				break;
			case USERPOSTS:
				listPosts(in, fsys);
				break;
			case FRIENDS:
				listFriends(in, fsys);
				break;
			case USERS:
				listUsers(in, fsys);
				break;
			case COMMENTSBYUSER:
				listComments(in, fsys);
				break;
			case TOPICFANATICS:
				listTopicFanatics(in, fsys);
				break;
			case TOPICPOSTS:
				listTopicPosts(in, fsys);
				break;
			case POPULARPOST:
				popularPosts(fsys);
				break;
			case TOPPOSTER:
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
	
	/**
	 * Sends error Message.
	 * @param in - Scanner
	 */
	private static void wrongCommand(Scanner in) {
		in.nextLine();
		System.out.printf(UNKNNOWN_COMMAND);
	}
	
	/**
	 * Registers a new user on fakebook.
	 * @param in - Scanner
	 * @param fsys - fakeSystem
	 */
	private static void addUser(Scanner in, FakeSystem fsys) {
		String kind = in.next();
		String userId = in.nextLine().trim();
		List<String> sequence = null;							
		
		int numberFanaticisms = 0;
		if(fsys.isFanatic(kind)) {
			numberFanaticisms = in.nextInt();
			sequence = new ArrayList<String>(numberFanaticisms*2) ;
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

	/**
	 * Lists all the friends of a user.
	 * @param in - Scanner 
	 * @param fsys - FakeSystem
	 */
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
	
	/**
	 * User posts a new message.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
	 */
	private static void newPost(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		int hashtagsNumber = in.nextInt();
		
		List<String> hashtags = new ArrayList<String>(hashtagsNumber);
		for(int i = 0; i < hashtagsNumber; i++) {
			hashtags.add(in.next());
		}
		String truthfulness = in.next();
		String message = in.nextLine().trim();
		
		try {
			fsys.newPost(userId, hashtagsNumber, hashtags, truthfulness, message);
			System.out.printf(SUCCESS_NEW_POST, userId, truthfulness, fsys.getNumFriends(userId), fsys.getNumPosts(userId));
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (InvalidHashtagsListException e) {
			System.out.println(ERROR_INVALID_HASHTANGS);
		} catch (InadequateStanceException e) {
			System.out.println(ERROR_INADEQUATE_COMMMENT_STANCE);
		}
	}
	
	/**
	 * User comments on a post.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
	 */
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
	
	/**
	 * Lists all registered users on fakebook.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
	 */
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
	
	/**
	 * Lists all the friends of a user.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
	 */
	private static void listFriends(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine().trim();
		
		try {
			Iterator<User> it = fsys.listFriends(userId);
			while(it.hasNext()) {
				System.out.print(it.next().getId());
				if(it.hasNext())
					System.out.print(COMA);
			}
			System.out.println(DOT);
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (NoFriendsException e) {
			System.out.printf(ERROR_NO_FRIENDS, userId);
		}
	}
	
    /**
     * Lists all posts by a user.
 	 * @param in - Scanner
	 * @param fsys - FakeSystem
     */
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
    
    /**
     * Lists all Comments of a Post.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
     */
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
    
    /**
     * Shows all the comments by a user on a particular topic.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
     */
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
    
    /**
     * Shows a list of fanatic users on a given topic.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
     */
    private static void listTopicFanatics(Scanner in, FakeSystem fsys) {
		String hashtag = in.next();
		
		try {
			Iterator<String> it = fsys.listFanaticsByTopic(hashtag);
			while(it.hasNext()) {
				System.out.printf(it.next());
				if(it.hasNext()) System.out.printf(COMA);
				else System.out.printf(DOT);						
			}
			System.out.println();
		} catch(UnknownFanaticismException e) {
			System.out.printf(ERROR_UNKNOWN_FANATICISM, hashtag);
		}
	}

	/**
	 * Shows a list of posts on a given topic.
	 * @param in - Scanner
	 * @param fsys - FakeSystem
	 */
	private static void listTopicPosts(Scanner in, FakeSystem fsys) {
		String hashtag = in.next();
		int numberOfPosts = in.nextInt();
		
		try {
			Iterator<Post> it = fsys.listTopicPosts(numberOfPosts, hashtag);
			while(it.hasNext() && numberOfPosts > 0) {										
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

	/**
	 * Shows the id of the top liar as well as statistics on how many lies (s)he made in the network.
	 * @param fsys - FakeSystem
	 */
	private static void shameless(FakeSystem fsys) {
		try {
			User user = fsys.getShameless();
			System.out.printf(SUCCESS_SHAMELESS, user.getId(), user.getNumberOfLies());
		} catch(NoKingOfLiarsException e) {
			System.out.println(ERROR_NO_KING_OF_LIARS);
		}
	}

	/**
	 * Shows the id of the user with the higher percentage of commented posts.
	 * @param fsys - FakeSystem
	 */
	private static void responsive(FakeSystem fsys) {
		try {
			User user = fsys.getResponsive();
			System.out.printf(SUCCESS_TOP_POSTER, user.getId(), user.getTotalNumberComments(), user.getNumCanCommentPosts());
		} catch (NoKingOfResponsivenessException e) {
			System.out.println(ERROR_NO_KING_OF_RESPONSIVENESS);
		}
	}

	/**
	 * Shows the id of the top poster.
	 * @param fsys - FakeSystem
	 */
	private static void topPoster(FakeSystem fsys) {
		try {
			User user = fsys.getTopPoster();
			System.out.printf(SUCCESS_TOP_POSTER, user.getId(), user.getNumberPosts(), user.getTotalNumberComments());
		} catch(NoKingPostersException e) {
			System.out.println(ERROR_NO_KING_POSTERS);
		}
	}

	/**
	 * Shows the most commented post
	 * @param fsys - FakeSystem
	 */
	private static void popularPosts(FakeSystem fsys) {
		try {
			Post post = fsys.getPopularPost();
			System.out.printf(SUCCESS_LIST_TOPIC_POSTS, post.getAuthorId(), post.getIdPost(), post.getNumComments(), post.getMessage());
		} catch(NoKingPopularPostException e) {
			System.out.println(ERROR_NO_KING_POPULAR_POST);
		}
	}

	/**
	 * Terminates the execution of the program.
	 */
	private static void exit() {
		System.out.println(SUCCESS_EXIT);
	}
	
	/**
	 * Shows the available commands. 
	 */
	private static void help() {
		for(Command cm : Command.values())
			if(!cm.equals(Command.UNKNOWN))
				System.out.printf("%s - %s\n", cm.toString().toLowerCase(), cm.getDescription());
	}
	
}

