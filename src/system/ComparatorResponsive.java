package system;

import java.util.Comparator;

import user.User;

public class ComparatorResponsive implements Comparator<User> {
	
	public int compare(User user, User responsive) {
		if(responsive == null || user.getPercentageCommentedPosts() > responsive.getPercentageCommentedPosts()) return 1;
		if(user.getPercentageCommentedPosts() == responsive.getPercentageCommentedPosts())
			if(user.getId().compareTo(responsive.getId()) > 0) return 1;
		return 0;
	}
}
