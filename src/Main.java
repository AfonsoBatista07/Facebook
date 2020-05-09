import java.util.Scanner;

import exceptions.InadequateStanceException;
import exceptions.InvalidCommentStanceException;
import exceptions.InvalidFanaticismListException;
import exceptions.InvalidHashtagsListException;
import exceptions.UnknownUserKindException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserCanNotComentPostException;
import exceptions.UserDoesNotExistException;
import exceptions.UserHasNoPostsException;
import exceptions.UserNoAccessToPostException;
import exceptions.UsersAlreadyFriendsException;
import system.FakeSystem;

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
 	private static final String SUCCESS_EXIT = "Bye!\n";
 	private static final String SUCCESS_ADD_FRIEND = "%s is friend of %s.\n";
 	private static final String SUCCESS_ADD_USER = "%s registered.\n";
 	private static final String SUCCESS_NEW_POST = "%s sent a %s post to %d friends. Post id = %s.\n";
 	private static final String SUCCESS_NEW_COMMENT = "Comment added!";
 	
 	/* Error Constants */
 	private static final String ERROR_INADEQUATE_COMMMENT_STANCE = "Inadequate stance!";
 	private static final String ERROR_INVALID_HASHTANGS = "Invalid hashtags list!";
 	private static final String ERROR_INVALID_COMMMENT_STANCE = "Invalid comment stance!";
 	private static final String ERROR_INVALID_FANATICISM = "Invalid fanaticism list!";
 	private static final String ERROR_NO_KING_OF_LIARS = "Social distancing has reached fakebook. Post a lie and become the king of liars.";
 	private static final String ERROR_NO_KING_OF_RESPONSIVENESS = "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";;
 	private static final String ERROR_NO_KING_POSTERS = "Social distancing has reached fakebook. Post something the king of posters.";
 	private static final String ERROR_NO_POST = "Social distancing has reached fakebook. Please post something.";
 	private static final String ERROR_NO_COMMENTS = "No comments!";
 	private static final String ERROR_UNKNOWN_FANATICISM = "Oh please, who would be a fanatic of %s?";
 	private static final String ERROR_UNKNOWN_TOPIC = "Oh please, whp would write about %s?";
 	private static final String ERROR_UNKNOWN_USER_KIND = "%s is an invalid user kind!\n";
 	private static final String ERROR_USER_ALREADY_EXISTS= "%s already exists!\n";
 	private static final String ERROR_USER_DOES_NOT_EXISTS = "%s does not exist!";
 	private static final String ERROR_USER_NO_POSTS =  "%s has no post %s!\n";  
 	private static final String ERROR_USER_NO_ACCESS_TO_POST = "%s has no access to post %s by %s!\n";
 	private static final String ERROR_USERS_FRIENDS_ALREADY = "%s must really admire %s!\n";
 	private static final String ERROR_USER_CAN_NOT_COMMENT = "%s cannot comment on this post!\n";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		FakeSystem fsys = new SystemClass();
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
		}
	}
	
	private static void addUser(Scanner in, FakeSystem fsys) {
		String kind = in.next();
		String userId = in.nextLine();
		
		int numberFanaticisms = 0;
		String[] sequence = {};
		
		try {
			if(fsys.isFanatic(kind)) {
				numberFanaticisms = in.nextInt();
				int counter = numberFanaticisms;
				while(counter<0) {
					sequence[counter--] = in.next();
				}
			}
			
			fsys.addUser(kind, userId, numberFanaticisms, sequence);
		} catch (UnknownUserKindException e) {
			System.out.printf(ERROR_UNKNOWN_USER_KIND, kind);
		} catch (UserAlreadyExistsException e) {
			System.out.printf(ERROR_USER_ALREADY_EXISTS, e.getUserId());
		} catch (InvalidFanaticismListException e) {
			System.out.println(ERROR_INVALID_FANATICISM);
		}
		System.out.printf(SUCCESS_ADD_USER, userId);
	}

	private static void addFriend(Scanner in, FakeSystem fsys) {
		String firstUserId = in.next();
		String secondUserId = in.next();
		
		try {
			fsys.addFriend(firstUserId, secondUserId);
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());
		} catch (UsersAlreadyFriendsException e) {
			System.out.printf(ERROR_USERS_FRIENDS_ALREADY, firstUserId, secondUserId);
		}
		System.out.printf(SUCCESS_ADD_FRIEND, firstUserId, secondUserId);
	}
	
	private static void newPost(Scanner in, FakeSystem fsys) {
		String userId = in.nextLine();
		int hashtagsNumber = in.nextInt();
		
		String[] hashtags = new String[hashtagsNumber]; 
		int counter = hashtagsNumber;
		while(counter<0) {
			hashtags[counter--] = in.next();
		}
		String truthfulness = in.next();
		String message = in.nextLine();
		
		try {
			fsys.newPost(userId, hashtagsNumber, hashtags, truthfulness, message);
		} catch (UserAlreadyExistsException e) {
			System.out.printf(ERROR_USER_ALREADY_EXISTS, e.getUserId());
		} catch (InvalidHashtagsListException e) {
			System.out.println(ERROR_INVALID_HASHTANGS);
		} catch (InadequateStanceException e) {
			System.out.println(ERROR_INADEQUATE_COMMMENT_STANCE);
		}
		System.out.printf(SUCCESS_NEW_POST, userId, truthfulness, fsys.getNumberFriends(userId), fsys.getPostId(userId));
	}
	
	private static void newComment(Scanner in, FakeSystem fsys) {
		
		String idUserComment = in.nextLine();
		String idUserAuthor = in.nextLine();
		String idPost = in.next();
		String stance = in.next();
		String comment = in.next();
		in.nextLine();
		
		try {
			fsys.addComment(idUserComment, idUserAuthor, idPost, stance, comment);
		} catch (UserDoesNotExistException e) {
			System.out.printf(ERROR_USER_DOES_NOT_EXISTS, e.getUserId());                                    
		} catch (UserNoAccessToPostException e) {
			System.out.printf(ERROR_USER_NO_ACCESS_TO_POST, idUserComment, idPost, idUserAuthor);
		} catch (UserHasNoPostsException e) {
			System.out.printf(ERROR_USER_NO_POSTS, idUserAuthor, idPost);
		} catch (UserCanNotComentPostException e) {
			System.out.printf(ERROR_USER_CAN_NOT_COMMENT, idUserComment);
		} catch (InvalidCommentStanceException e) {
			System.out.println(ERROR_INVALID_COMMMENT_STANCE);
		}
		System.out.println(SUCCESS_NEW_COMMENT);
	}
	
	private static void shameless(FakeSystem fsys) {
		
	}

	private static void responsive(FakeSystem fsys) {
		
	}

	private static void topPoster(FakeSystem fsys) {
		
	}

	private static void popularPosts(FakeSystem fsys) {
		
	}

	private static void listTopicPosts(Scanner in, FakeSystem fsys) {
		
	}

	private static void listTopicFanatics(Scanner in, FakeSystem fsys) {
		
	}

	private static void listComments(Scanner in, FakeSystem fsys) {
		
	}

	private static void listUsers(Scanner in, FakeSystem fsys) {
		
	}

	private static void readPost(Scanner in, FakeSystem fsys) {

	}

	private static void listFriends(Scanner in, FakeSystem fsys) {
		
	}

	private static void listPosts(Scanner in, FakeSystem fsys) {
		
	}

	private static void exit() {
		System.out.println(SUCCESS_EXIT);
	}
	
	private static void help() {
		System.out.print("register - registers a new user\n"
				+ "users - lists all rusers\n"
				+ "addfriend - adds a new friend\n"
				+ "friends - list the user friends\n"
				+ "post - posts a new message\n"
				+ "userposts - lists all posts by a user\n"
				+ "comment - user comments on a post\n"
				+ "readpost - prints detailed info on a post\n"
				+ "commentsbyuser - shows all tge comments by a user on a given post\n"
				+ "topicfanatic - shows a list of posts on a given topic\n"
				+ "topicposts - shows the most commented post\n"
				+ "topposter - shows the user with more posts\n"
				+ "responsive - shows the user with a higher percentage of commented posts\n"
				+ "shameless - shows the top liars\n"
				+ "help - shows the available commands\n"
				+ "exit - terminates the execution of the program\n");
	}
	
}

