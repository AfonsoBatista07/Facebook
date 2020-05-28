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
	 * @return 1 if user have a higher percentage of commented posts than the responsive,
	 * if there is a draw, return 1 if user have a user id alphabetically bigger than responsive.
	 */
	public int compare(User user, User responsive) {
		if(responsive == null || user.getPercentageCommentedPosts() > responsive.getPercentageCommentedPosts()) return 1;
		if(user.getPercentageCommentedPosts() == responsive.getPercentageCommentedPosts())
			if(user.getId().compareTo(responsive.getId()) > 0) return 1;
		return -1;
	}
}
