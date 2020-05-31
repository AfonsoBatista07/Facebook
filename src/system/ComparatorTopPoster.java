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
	 * @return >0 if user the has more posts than the topPoster, if there is a tie,
	 * if the user has wrote more comments than the topPoster, if there is still a tie,
	 * if the user's id is alphabetically first than the topPoster's id.
	 */
	public int compare(User user, User topPoster) {
		if(topPoster == null) return 1;
		int i = user.getNumberPosts() - topPoster.getNumberPosts();
		if(i != 0) return i;
		
		i = topPoster.getTotalNumberComments() - user.getTotalNumberComments();
		if(i != 0) return i;
		
		i = topPoster.getId().compareTo(user.getId());
		if(i != 0) return i;
		
		return 0;
	}
}
