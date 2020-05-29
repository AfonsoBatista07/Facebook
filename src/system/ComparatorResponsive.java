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
	 * if there is a draw, return 1 if user has a user id alphabetically bigger than responsive.
	 */
	public int compare(User user, User responsive) {
		if(responsive == null || user.getPercentageCommentedPosts() > responsive.getPercentageCommentedPosts()) return 1;
		
		float i = user.getPercentageCommentedPosts() - responsive.getPercentageCommentedPosts();
		if(i != 0) return (int) i;
		
		int f = user.getId().compareTo(responsive.getId());
		if(f != 0) return f;
		
		return 0;
	}
}
