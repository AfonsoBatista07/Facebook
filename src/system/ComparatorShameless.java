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
	 * @return value>0 if user have more lies than the shameless or, if they have the same, 
	 * if user have lower sum of posts and comments, if the ties still remain, 
	 * if user have a user id alphabetically bigger than shameless 
	 */
	public int compare(User user, User shameless) {
		int i = shameless.getNumberOfLies() - user.getNumberOfLies();
		if(i != 0) return i;

		int shamelessSum = shameless.getTotalNumberComments() + shameless.getNumberPosts();
		int userSum = user.getTotalNumberComments() + user.getNumberPosts();
		
		i = userSum - shamelessSum;
		if(i != 0) return i;
		
		i = user.getId().compareTo(shameless.getId());
		if(i != 0) return i;

		return 0;
	}
}
