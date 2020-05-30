package system;

import java.util.Comparator;

import user.User;

/**
 * Comparator Class for responsive user.
 * @author Afonso Batista 57796
 * @author Joao Jorge 57994
 */
public class ComparatorResponsive implements Comparator<User> {
	
	/**
	 * @param user - User
	 * @param responsive - Responsive user.
	 * @return 1 if user has a higher percentage of commented posts than the responsive,
	 * if there is a draw, return 1 if the user's id is alphabetically first than the responsive's id.
	 */
	public int compare(User user, User responsive) {
		if(responsive == null) return 1;
		
		if(user.getPercentageCommentedPosts() > responsive.getPercentageCommentedPosts()) return 1;
		if(user.getPercentageCommentedPosts() < responsive.getPercentageCommentedPosts()) return -1;
		
		int f = user.getId().compareTo(responsive.getId());
		if(f != 0) return f;
		
		return 0;
	}
}
