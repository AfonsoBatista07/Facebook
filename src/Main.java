import java.util.Scanner;

public class Main {

	/* Commands Constants */
	private static final String ADD_USER = "ADD" ;
	private static final String ADD_FRIEND = "ADDFRIEND";
	private static final String REGISTER = "REGISTER";
	private static final String POST = "POST";
	private static final String USER_POSTS = "USERPOSTS";
	private static final String COMMENT = "COMMENT";
	private static final String LIST_FRIENDS = "FRIENDS";
	private static final String LIST_USERS = "USERS";
	private static final String READ = "READPOST";
	private static final String USER_COMMENTS = "COMMENTSBYUSER";
	private static final String TOPIC_FANATICS = "TOPICFANATICS";
	private static final String TOPIC_POSTS = "TOPICPOSTS";
	private static final String POPULAR_POST = "POPULARPOST";
	private static final String TOP_POSTER = "TOPPOSTER";
	private static final String RESPONSIVE = "RESPONSIVE";
	private static final String SHAMELESS = "SHAMELESS";
	private static final String EXIT = "EXIT";
	private static final String HELP = "HELP";
	
	/* Success Constants */
 	private static final String SUCCESS_EXIT = "Bye!";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//System sys = new SystemClass();
		String cm;
		do{
			cm = readOption(in);
			exeOption(in, cm);
			System.out.println("");
		}while(!cm.equals(EXIT));
		in.close();
		
	}
	
	private static String readOption( Scanner in ) {
		return in.next().toUpperCase();
	}
	
	private static void exeOption( Scanner in, String option ) {
		switch(option) {
			case ADD_USER:
				break;
			case ADD_FRIEND:
				break;
			case REGISTER:
				break;
			case POST:
				break;
			case USER_POSTS:
				break;
			case COMMENT:
				break;
			case LIST_FRIENDS:
				break;
			case LIST_USERS:
				break;
			case READ:
				break;
			case TOPIC_FANATICS:
				break;
			case TOPIC_POSTS:
				break;
			case POPULAR_POST:
				break;
			case TOP_POSTER:
				break;
			case RESPONSIVE:
				break;
			case SHAMELESS:
				break;
			case USER_COMMENTS:
				break;
			case HELP:
				help();
				break;
			case EXIT:
				exit();
				break;
		}
	}
	
	private static void exit() {
		System.out.printf(SUCCESS_EXIT);
	}
	
	private static void help() {
		System.out.printf("register - registers a new user\n"
				+ "users - lists all rusers\n"
				+ "addfriend - adds a new friend\n"
				+ "friends - list the user friends\n"
				+ "post - posts a new message\n"
				+ "userposts - lists all posts by a user\n"
				+ "comment - user comments on a post\n"
				+ "readpost - prints detailed info on a post\n"
				+ " commentsbyuser - shows all tge comments by a user on a given post\n"
				+ "topicfanatic - shows a list of posts on a given topic\n"
				+ "topicposts - shows the most commented post\n"
				+ "topposter - shows the user with more posts\n"
				+ "responsive - shows the user with a higher percentage of commented posts\n"
				+ "shameless - shows the top liars\n"
				+ "help - shows the available commands\n"
				+ "exit - terminates the execution of the program\n");
	}
}

