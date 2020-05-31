package system;

import java.util.Comparator;

import user.User;

/**
 * Comparator Class for shameless user.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class ComparatorShameless implements Comparator<User>{

	/** 
	 * @param user - User.
	 * @param shameless - Shameless.
	 * @return >0 if user has lower sum of posts and comments, if the ties still remain, 
	 * if the user's id is alphabetically first than the shameless's id 
	 */
	public int compare(User user, User shameless) {

		int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
		int userSum = user.getTotalNumberComments() + user.getNumberPosts();
		
		int i = userSum - shamelessSum;
		if(i != 0) return i;
		
		i = user.getId().compareTo(shameless.getId());
		if(i != 0) return i;

		return 0;
	}
}
