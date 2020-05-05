import java.util.Scanner;

import exceptions.InadequateStanceException;
import exceptions.InvalidFanaticismListException;
import exceptions.UnknownUserKindException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserCanNotComentPostException;
import exceptions.UserDoesNotExistExeption;
import exceptions.UserHasNoPostsException;
import exceptions.UserNoAccessToPostException;
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
 	private static final String SUCCESS_EXIT = "Bye!";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		FakeSystem fsys = new SystemClass();
		String cm;
		do{
			cm = readOption(in);
			exeOption(in, fsys, cm);
			System.out.println("");
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
		try {
			String type = in.next();
			String id = in.nextLine();
			
			int numberFanaticisms = 0;
			String[] sequence = {};
			
			if(fsys.isFanatic(type)) {
				numberFanaticisms = in.nextInt();
				int counter = numberFanaticisms;
				while(counter<0) {
					sequence[counter--] = in.next();
				}
			}
			fsys.addUser(type, id, numberFanaticisms, sequence);
		} catch (UnknownUserKindException e) {
			System.out.println(e.getMessage());
		} catch (UserAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (InvalidFanaticismListException e) {
			System.out.println(e.getMessage());
		}
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
		} catch (UserDoesNotExistExeption e) {
			System.out.printf(e.getMessage(), idUserComment);           
		//} catch (UserDoesNotExistExeption e) {                              // BETTER WAY ????????????????????????       
			//System.out.printf(e.getMessage(), idUserAuthor);                         
		} catch (UserNoAccessToPostException e) {
			System.out.printf(e.getMessage(), idUserComment, idPost, idUserAuthor);
		} catch (UserHasNoPostsException e) {
			System.out.printf(e.getMessage(), idUserAuthor, idPost);
		} catch (UserCanNotComentPostException e) {
			System.out.printf(e.getMessage(), idUserComment);
		} catch (InadequateStanceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void newPost(Scanner in, FakeSystem fsys) {
	
	}

	private static void addFriend(Scanner in, FakeSystem fsys) {
	
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

