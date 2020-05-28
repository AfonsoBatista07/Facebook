package system;

import java.util.Comparator;

import user.User;

/**
 * Comparator Class for top poster user.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class ComparatorTopPoster implements Comparator<User> {
	
	/**
	 * @param user - User
	 * @param topPoster - Top poster user.
	 * @return 1 if user have more posts than the topPoster, if there is a tie,
	 * 1 if user has wrote more comments than the topPoster, if there is still a tie,
	 * 1 if user a user id alphabetically bigger than topPoster.
	 */
	public int compare(User user, User topPoster) {
		if(topPoster == null || user.getNumberPosts() > topPoster.getNumberPosts()) return 1;
		else if(user.getNumberPosts() == topPoster.getNumberPosts()) {
			if(user.getTotalNumberComments() > topPoster.getTotalNumberComments()) return 1;
			else if(user.getTotalNumberComments() == topPoster.getTotalNumberComments())
				if(user.getId().compareTo(topPoster.getId()) < 0) return 1;
		}
		return -1;
	}
}
